package vn.mcare.system.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.dto.MedicalReportDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.MedicalReportRepo;
import vn.mcare.system.repository.model.tables.MedicalReport;
import vn.mcare.system.repository.model.tables.records.MedicalReportRecord;

@Repository
@Slf4j
public class MedicalReportRepoImpl extends BaseRepoImpl<String, MedicalReportRecord, MedicalReport> implements MedicalReportRepo {


  @Override
  public MedicalReportDto getByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      MedicalReport medicalReport = MedicalReport.MEDICAL_REPORT;

      return context
              .selectFrom(medicalReport)
              .where(medicalReport.CIF.eq(cif))
              .fetchOneInto(MedicalReportDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
