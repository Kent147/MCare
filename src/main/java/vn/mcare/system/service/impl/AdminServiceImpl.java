package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.helper.PassHelper;
import vn.mcare.system.common.pojo.api.input.CreateAdminInput;
import vn.mcare.system.common.pojo.api.input.UpdateAdminInput;
import vn.mcare.system.common.pojo.dto.AdminDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.AdminRepo;
import vn.mcare.system.repository.model.tables.records.AdminRecord;
import vn.mcare.system.service.intface.AdminService;
import vn.mcare.system.common.exception.SQLException.SQLExcuteError;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminServiceImpl implements AdminService {

    private final Gson gson;
    private final AdminRepo adminRepo;

    @Override
    public RestfulCommonResponse getAdmins() {
        try {

            List<AdminDto> output = adminRepo.getAll();

            return new RestfulSuccessResponse().setData(gson.toJsonTree(output));

        } catch (Exception e) {
            log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
            return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
        }
    }

    @Override
    public RestfulCommonResponse createAdmin(JsonObject payload) {
        try {

            CreateAdminInput input = gson.fromJson(payload, CreateAdminInput.class);
            adminRepo.checkValidAdmin(input.getEmail());

            String salt = PassHelper.genSalt();
            String hashedPassword = PassHelper.hashPw(input.getPassword(), salt);

            AdminRecord record = new AdminRecord()
                    .setCif(Generator.generate())
                    .setEmail(input.getEmail())
                    .setFullName(input.getFullName())
                    .setPassword(hashedPassword)
                    .setSalt(salt)
                    .setDescription(input.getDescription())
                    .setCreatedAt(LocalDateTime.now())
                    .setModifiedAt(LocalDateTime.now());
            adminRepo.save(record);
            return new RestfulSuccessResponse().setMessages("Save admin success");
        } catch (SQLExcuteError e) {
            return new RestfulFailureResponse().setMessages(e.getMessage());
        } catch (Exception e) {
            log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
            return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
        }
    }

    @Override
    public RestfulCommonResponse updateAdmin(JsonObject payload) {
        try {
            UpdateAdminInput input = gson.fromJson(payload, UpdateAdminInput.class);

            adminRepo.getAdmin(input.getAdminId());

            AdminRecord record = new AdminRecord()
                    .setCif(input.getAdminId())
                    .setEmail(input.getEmail())
                    .setFullName(input.getFullName())
                    .setDescription(input.getDescription())
                    .setModifiedAt(LocalDateTime.now());
            System.out.println(record.toString());
            adminRepo.update(record);

            return new RestfulSuccessResponse().setMessages("Update admin success");
        } catch (SQLExcuteError e) {
            return new RestfulFailureResponse().setMessages(e.getMessage());
        } catch (Exception e) {
            log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
            return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
        }
    }

    @Override
    public RestfulCommonResponse deleteAdmin(String adminId) {
        try {

            adminRepo.delete(adminId);

            return new RestfulSuccessResponse().setMessages("Delete admin success");
        } catch (SQLExcuteError e) {
            return new RestfulFailureResponse().setMessages(e.getMessage());
        } catch (Exception e) {
            log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
            return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
        }
    }
}