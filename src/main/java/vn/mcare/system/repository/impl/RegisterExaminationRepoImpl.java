package vn.mcare.system.repository.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.output.RegisterExaminationOutput;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.RegisterExaminationRepo;
import vn.mcare.system.repository.model.tables.RegisterExamination;
import vn.mcare.system.repository.model.tables.records.RegisterExaminationRecord;

@Slf4j
@Repository
public class RegisterExaminationRepoImpl extends
        BaseRepoImpl<Integer, RegisterExaminationRecord, RegisterExamination> implements
        RegisterExaminationRepo {

  @Override
  public Boolean checkExistRandomNumber(String randomNumber) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      RegisterExamination reg = RegisterExamination.REGISTER_EXAMINATION;

      context
              .selectFrom(reg)
              .where(reg.RANDOM_NUMBER.eq(randomNumber))
              .fetch()
              .get(0);

      return true;
    } catch (IndexOutOfBoundsException e) {
      return false;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public LocalDateTime getNextDate() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      RegisterExamination reg = RegisterExamination.REGISTER_EXAMINATION;

      RegisterExaminationRecord record = context
              .selectFrom(reg)
              .orderBy(reg.ID.desc())
              .fetch()
              .get(0);

      return record.getFromDate().plusMinutes(10);

    } catch (IndexOutOfBoundsException e) {
      return LocalDate.now().atTime(8, 30);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<RegisterExaminationOutput> getAll() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
              .selectFrom(RegisterExamination.REGISTER_EXAMINATION)
              .fetchInto(RegisterExaminationOutput.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
