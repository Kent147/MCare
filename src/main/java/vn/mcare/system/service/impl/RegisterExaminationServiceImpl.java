package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.input.RegChangeStatusInput;
import vn.mcare.system.common.pojo.api.output.RegisterExaminationOutput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.CustomerRepo;
import vn.mcare.system.repository.intface.RegisterExaminationRepo;
import vn.mcare.system.repository.model.tables.records.CustomerRecord;
import vn.mcare.system.repository.model.tables.records.RegisterExaminationRecord;
import vn.mcare.system.service.intface.RegisterExaminationService;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RegisterExaminationServiceImpl implements RegisterExaminationService {

  private final Gson gson;
  private final RegisterExaminationRepo registerExaminationRepo;
  private final CustomerRepo customerRepo;

  @Override
  public RestfulCommonResponse getAll() {
    try {
      List<RegisterExaminationOutput> output = registerExaminationRepo.getAll();
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse changeStatus(JsonObject payload) {
    try {
      RegChangeStatusInput input = gson.fromJson(payload, RegChangeStatusInput.class);

      RegisterExaminationRecord record = registerExaminationRepo.findOne(input.getId());
      if (record != null) {
        record.setStatus(input.getStatus());
        registerExaminationRepo.update(record);
      } else {
        throw new SQLException.RecordNotFound("Not found register examination");
      }

      return new RestfulSuccessResponse();
    } catch (SQLException.RecordNotFound e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse receiveReg(Integer id) {
    try {
      RegisterExaminationRecord input = registerExaminationRepo.findOne(id);

      if (input != null) {
        CustomerRecord record = new CustomerRecord();
        record.setCif(Generator.generate())
                .setFullName(input.getFullName())
                .setEmail(input.getEmail())
                .setPhone(input.getPhone())
                .setModifiedAt(LocalDateTime.now())
                .setCreatedAt(LocalDateTime.now());

        customerRepo.save(record);
      } else {
        throw new SQLException.RecordNotFound("Not found register examination");
      }

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (SQLException.RecordNotFound e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

}
