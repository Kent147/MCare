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
import vn.mcare.system.common.exception.SQLException.RecordExisted;
import vn.mcare.system.common.exception.SQLException.RecordNotFound;
import vn.mcare.system.common.exception.SQLException.SQLExcuteError;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.helper.PassHelper;
import vn.mcare.system.common.pojo.api.input.CreateUserInput;
import vn.mcare.system.common.pojo.api.input.UpdateUserInput;
import vn.mcare.system.common.pojo.api.output.GetAllUserOutput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.UserRepo;
import vn.mcare.system.repository.model.tables.records.UserRecord;
import vn.mcare.system.service.intface.UserService;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {

  private final Gson gson;
  private final UserRepo userRepo;

  @Override
  public RestfulCommonResponse fetchAll() {
    try {
      List<GetAllUserOutput> output = userRepo.getAll();
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulSuccessResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse save(JsonObject payload) {
    try {
      CreateUserInput input = gson.fromJson(payload, CreateUserInput.class);
      userRepo.checkValidUser(input.getEmail());

      String salt = PassHelper.genSalt();
      String hashedPassword = PassHelper.hashPw(input.getPassword(), salt);

      UserRecord record =
              new UserRecord()
                      .setCif(Generator.generate())
                      .setEmail(input.getEmail())
                      .setFullName(input.getFullName())
                      .setAddress(input.getAddress())
                      .setCardId(input.getCardId())
                      .setGender(input.getGender())
                      .setBod(DateHelper.toLocalDate(input.getBod()))
                      .setDescription(input.getDescription())
                      .setPassword(hashedPassword)
                      .setSalt(salt)
                      .setRole(input.getRole())
                      .setCreatedAt(LocalDateTime.now())
                      .setModifiedAt(LocalDateTime.now());

      userRepo.save(record);
      return new RestfulSuccessResponse().setMessages("Save user success");
    } catch (RecordExisted e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse update(JsonObject payload) {
    try {
      UpdateUserInput input = gson.fromJson(payload, UpdateUserInput.class);
      userRepo.getUser(input.getCif());
      if (input.getRole() == null) {
        input.setRole(0);
      }
      UserRecord record =
              new UserRecord()
                      .setCif(input.getCif())
                      .setEmail(input.getEmail())
                      .setFullName(input.getFullName())
                      .setAddress(input.getAddress())
                      .setCardId(input.getCardId())
                      .setGender(input.getGender())
                      .setRole(input.getRole())
                      .setBod(DateHelper.toLocalDate(input.getBirthday()))
                      .setDescription(input.getDescription())
                      .setModifiedAt(LocalDateTime.now());
      userRepo.update(record);
      return new RestfulSuccessResponse().setMessages("Update user success");
    } catch (SQLExcuteError | RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse delete(String userId) {
    try {
      userRepo.delete(userId);
      return new RestfulSuccessResponse().setMessages("Delete user success");
    } catch (SQLExcuteError e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse checkValidEmail(String email) {
    RestfulCommonResponse res = new RestfulCommonResponse()
            .setCode(200).setStatus(true).setMessages("OK");
    try {
      userRepo.checkValidUser(email);
    } catch (RecordExisted e) {
      res.setStatus(false).setMessages("Valid email");
    } catch (Exception e) {
      res.setStatus(false).setMessages(e.getMessage());
    }
    return res;
  }

  @Override
  public RestfulCommonResponse checkValidCardId(String cardId) {
    RestfulCommonResponse res = new RestfulCommonResponse()
            .setCode(200).setStatus(true).setMessages("OK");
    try {
      userRepo.checkValidCardId(cardId);
    } catch (RecordExisted e) {
      res.setStatus(false).setMessages("Valid card id");
    } catch (Exception e) {
      res.setStatus(false).setMessages(e.getMessage());
    }
    return res;
  }
}
