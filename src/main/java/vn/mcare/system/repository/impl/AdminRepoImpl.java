package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.dto.AdminDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.AdminRepo;
import vn.mcare.system.repository.model.tables.Admin;
import vn.mcare.system.repository.model.tables.records.AdminRecord;

@Repository
@Slf4j
public class AdminRepoImpl extends BaseRepoImpl<String, AdminRecord, Admin> implements AdminRepo {

  @Override
  public List<AdminDto> getAll() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Admin admin = Admin.ADMIN;

      return context
          .selectFrom(admin)
          .fetchInto(AdminDto.class);

    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public void checkValidAdmin(String email) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      context
          .selectFrom(Admin.ADMIN)
          .where(Admin.ADMIN.EMAIL.eq(email))
          .fetch()
          .get(0);
      throw new SQLException.RecordExisted("Email existed");
    } catch (IndexOutOfBoundsException e) {
      log.debug("Email can use");
    } catch (SQLException.RecordExisted e) {
      log.error(e.getMessage());
      throw e;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public AdminDto getAdmin(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      return context
          .selectFrom(Admin.ADMIN)
          .where(Admin.ADMIN.CIF.eq(cif))
          .fetchInto(AdminDto.class)
          .get(0);
    } catch (IndexOutOfBoundsException e) {
      log.error("Admin not found");
      throw new SQLException.RecordNotFound("Admin not found");
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

}
