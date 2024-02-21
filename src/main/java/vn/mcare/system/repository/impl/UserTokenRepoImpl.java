package vn.mcare.system.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.ETokenStatus;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.input.ChangePasswordInput;
import vn.mcare.system.common.pojo.dto.UserTokenDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.UserTokenRepo;
import vn.mcare.system.repository.model.tables.UserToken;
import vn.mcare.system.repository.model.tables.records.UserTokenRecord;
import vn.mcare.system.common.constant.enums.ETokenStatus;

@Slf4j
@Repository
public class UserTokenRepoImpl extends BaseRepoImpl<Integer, UserTokenRecord, UserToken>
        implements UserTokenRepo {

  @Override
  public UserTokenDto getByToken(String token) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
              .selectFrom(UserToken.USER_TOKEN)
              .where(UserToken.USER_TOKEN.TOKEN.eq(token))
              .and(UserToken.USER_TOKEN.STATUS.eq(ETokenStatus.NORMAL.getCode()))
              .fetchInto(UserTokenDto.class)
              .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("Token not found: " + e);
      throw new SQLException.RecordNotFound("Token not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public void setFalseTokenByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      context
          .update(UserToken.USER_TOKEN)
          .set(UserToken.USER_TOKEN.STATUS, ETokenStatus.BANNED.getCode())
          .where(UserToken.USER_TOKEN.CIF.eq(cif))
          .execute();
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
