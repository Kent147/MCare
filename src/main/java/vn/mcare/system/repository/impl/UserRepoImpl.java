package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.exception.SQLException.RecordExisted;
import vn.mcare.system.common.exception.SQLException.RecordNotFound;
import vn.mcare.system.common.pojo.api.output.GetAllUserOutput;
import vn.mcare.system.common.pojo.dto.UserDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.UserRepo;
import vn.mcare.system.repository.model.tables.User;
import vn.mcare.system.repository.model.tables.records.UserRecord;

@Repository
@Slf4j
public class UserRepoImpl extends BaseRepoImpl<String, UserRecord, User> implements UserRepo {

  @Override
  public List<GetAllUserOutput> getAll() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context.selectFrom(User.USER).fetchInto(GetAllUserOutput.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public void checkValidUser(String email) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      context.selectFrom(User.USER).where(User.USER.EMAIL.eq(email)).fetch().get(0);
      throw new RecordExisted("Email existed");
    } catch (IndexOutOfBoundsException e) {
      log.debug("Email can use");
    } catch (RecordExisted e) {
      log.error(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public void checkValidCardId(String cardId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      context.selectFrom(User.USER).where(User.USER.CARD_ID.eq(cardId)).fetch().get(0);
      throw new RecordExisted("Email existed");
    } catch (IndexOutOfBoundsException e) {
      log.debug("Email can use");
    } catch (RecordExisted e) {
      log.error(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public UserDto getUser(String userId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
          .selectFrom(User.USER)
          .where(User.USER.CIF.eq(userId))
          .fetchInto(UserDto.class)
          .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("User not found");
      throw new RecordNotFound("User not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public UserDto getUserByEmail(String email) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
          .selectFrom(User.USER)
          .where(User.USER.EMAIL.eq(email))
          .fetchInto(UserDto.class)
          .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("User not found");
      throw new RecordNotFound("User not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public UserDto getUserByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
          .selectFrom(User.USER)
          .where(User.USER.CIF.eq(cif))
          .fetchInto(UserDto.class)
          .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("User not found");
      throw new RecordNotFound("User not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public void changePasswordByCif(String cif, String hashPw, String salt) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      context
          .update(User.USER)
          .set(User.USER.PASSWORD, hashPw)
          .set(User.USER.SALT, salt)
          .where(User.USER.CIF.eq(cif))
          .execute();
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
