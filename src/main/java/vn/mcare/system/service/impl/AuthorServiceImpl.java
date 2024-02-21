package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.ETokenStatus;
import vn.mcare.system.common.constant.enums.ETokenType;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.AuthenticationException;
import vn.mcare.system.common.exception.AuthenticationException.InvalidUserException;
import vn.mcare.system.common.exception.SQLException.RecordNotFound;
import vn.mcare.system.common.helper.PassHelper;
import vn.mcare.system.common.helper.jwt.JwtProvider;
import vn.mcare.system.common.pojo.api.input.ChangePasswordInput;
import vn.mcare.system.common.pojo.api.input.UserLoginInput;
import vn.mcare.system.common.pojo.api.output.UserLoginOutput;
import vn.mcare.system.common.pojo.dto.UserDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.repository.intface.UserRepo;
import vn.mcare.system.repository.intface.UserTokenRepo;
import vn.mcare.system.repository.model.tables.records.UserTokenRecord;
import vn.mcare.system.service.intface.AuthorService;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorServiceImpl implements AuthorService {

  private final Gson gson;
  private final UserRepo userRepo;
  private final JwtProvider jwtProvider;
  private final UserTokenRepo userTokenRepo;

  @Override
  public RestfulCommonResponse userLogin(JsonObject payload) {
    try {
      UserLoginInput input = gson.fromJson(payload, UserLoginInput.class);
      UserDto userDto = userRepo.getUserByEmail(input.getEmail());
      if (!userDto
          .getPassword()
          .equals(PassHelper.hashPw(input.getPassword(), userDto.getSalt()))) {
        throw new AuthenticationException.InvalidUserException("Invalid User");
      }

      String token = jwtProvider.genJwt(userDto.getCif());
      log.debug("Token: " + token);
      saveUserToken(userDto, token);

      UserLoginOutput output =
          new UserLoginOutput()
              .setCif(userDto.getCif())
              .setEmail(userDto.getEmail())
              .setFullName(userDto.getFullName())
              .setToken(token)
              .setExpiredDate(
                  LocalDateTime.now()
                      .plusDays(1)
                      .atZone(ZoneId.systemDefault())
                      .toInstant()
                      .toEpochMilli());

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (InvalidUserException | RecordNotFound e) {
      log.error("Invalid User: " + e);
      return new RestfulFailureResponse().setResponse(CodeResponse.UNAUTHORIZED);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  private void saveUserToken(UserDto userDto, String token) {
    UserTokenRecord userTokenRecord =
        new UserTokenRecord()
            .setCif(userDto.getCif())
            .setToken(token)
            .setTokenType(ETokenType.ACCESS_TOKEN.getCode())
            .setStatus(ETokenStatus.NORMAL.getCode())
            .setExpireDate(LocalDateTime.now().plusDays(1))
            .setCreatedAt(LocalDateTime.now())
            .setModifiedAt(LocalDateTime.now());
    userTokenRepo.save(userTokenRecord);
  }

  @Override
  public RestfulCommonResponse userLogout(String cif) {
    RestfulCommonResponse response = null;
    try {
      userTokenRepo.setFalseTokenByCif(cif);
      response = new RestfulSuccessResponse();
    } catch (Exception e) {
      log.error(e.getMessage());
      response = new RestfulFailureResponse();
    }
    return response;
  }

  @Override
  public RestfulCommonResponse changePassword(JsonObject payload) {
    try {
      ChangePasswordInput input = gson.fromJson(payload, ChangePasswordInput.class);
      UserDto user = userRepo.getUserByCif(input.getCif());
      if (PassHelper.checkPw(input.getOldPassword(), user.getPassword())) {
        String salt = PassHelper.genSalt();
        String hash = PassHelper.hashPw(input.getNewPassword(), salt);
        userRepo.changePasswordByCif(input.getCif(), hash, salt);
      } else {
        return new RestfulFailureResponse().setMessages("Wrong old password");
      }
      return new RestfulSuccessResponse().setMessages("Change password success");
    } catch (Exception e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    }
  }
}
