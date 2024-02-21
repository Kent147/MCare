package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.output.MedicalHistoryDetail;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.MedicalHistoryRepo;
import vn.mcare.system.repository.model.tables.MedicalHistory;
import vn.mcare.system.repository.model.tables.MedicalReport;
import vn.mcare.system.repository.model.tables.MedicalReportDetail;
import vn.mcare.system.repository.model.tables.records.MedicalHistoryRecord;

@Slf4j
@Repository
public class MedicalHistoryRepoImpl extends
    BaseRepoImpl<Integer, MedicalHistoryRecord, MedicalHistory> implements MedicalHistoryRepo {

  @Override
  public List<MedicalHistoryDetail> getMedicalHistoryByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {

      MedicalHistory mh = MedicalHistory.MEDICAL_HISTORY;
      MedicalReport mr = MedicalReport.MEDICAL_REPORT;
      MedicalReportDetail mrd = MedicalReportDetail.MEDICAL_REPORT_DETAIL;

      return context
          .selectFrom(mh)
          .where(
              mh.EXAMINE_ID
                  .in(
                      context
                          .select(mrd.EXAMINE_ID)
                          .from(mrd)
                          .leftJoin(mr)
                          .on(
                              mrd.MEDICAL_REPORT_ID
                                  .like(mr.MEDICAL_REPORT_ID)
                          )
                          .and(
                              mr.CIF.like(cif)
                          )
                  )
          )
          .fetchInto(MedicalHistoryDetail.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
