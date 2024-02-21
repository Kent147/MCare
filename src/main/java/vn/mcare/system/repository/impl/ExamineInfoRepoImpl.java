package vn.mcare.system.repository.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SelectConditionStep;
import org.jooq.SelectOnConditionStep;
import org.jooq.SelectSeekStep1;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EDataList;
import vn.mcare.system.common.constant.enums.EExamineStatus;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.pojo.api.input.GetReceiveCustomerListInput;
import vn.mcare.system.common.pojo.api.output.ReceiveCustomerListOutput;
import vn.mcare.system.common.pojo.dto.ExamineDetailDto;
import vn.mcare.system.common.pojo.dto.ExamineDoneDto;
import vn.mcare.system.common.pojo.dto.ExamineDto;
import vn.mcare.system.common.pojo.dto.ExamineTodayDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.ExamineInfoRepo;
import vn.mcare.system.repository.model.tables.Customer;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.ExamineInfo;
import vn.mcare.system.repository.model.tables.MedicalReport;
import vn.mcare.system.repository.model.tables.MedicalReportDetail;
import vn.mcare.system.repository.model.tables.User;
import vn.mcare.system.repository.model.tables.records.ExamineInfoRecord;

@Slf4j
@Repository
public class ExamineInfoRepoImpl extends BaseRepoImpl<String, ExamineInfoRecord, ExamineInfo> implements ExamineInfoRepo {

  @Override
  public List<ExamineDto> getByMedicalReport(String medicalReportId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      ExamineInfo examine = ExamineInfo.EXAMINE_INFO;
      MedicalReportDetail medicalDetail = MedicalReportDetail.MEDICAL_REPORT_DETAIL;
      User user = User.USER;
      DataList dataList = DataList.DATA_LIST;

      return context
              .select(
                      examine.EXAMINE_ID,
                      user.FULL_NAME.as("attendDoctor"),
                      examine.CHECK_IN,
                      examine.CHECK_OUT,
                      examine.DIAGNOSE,
                      dataList.LABEL.as("status")
              )
              .from(examine)
              .leftJoin(medicalDetail)
              .on(examine.EXAMINE_ID.eq(medicalDetail.EXAMINE_ID))
              .leftJoin(user)
              .on(examine.ATTEND_DOCTOR.eq(user.CIF))
              .leftJoin(dataList)
              .on(examine.STATUS.cast(String.class).eq(dataList.DATA_VALUE))
              .where(medicalDetail.MEDICAL_REPORT_ID.eq(medicalReportId))
              .and(dataList.DATA_KEY.eq(EDataList.EXAMINE_STATUS.getKey()))
              .fetchInto(ExamineDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public ExamineDetailDto getExaminingDetail(String medicalReportId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      ExamineInfo examine = ExamineInfo.EXAMINE_INFO;
      MedicalReportDetail medicalDetail = MedicalReportDetail.MEDICAL_REPORT_DETAIL;
      User user = User.USER;
      DataList dataList = DataList.DATA_LIST;

      SelectSeekStep1 query = context.select(
              examine.EXAMINE_ID,
              examine.ATTEND_DOCTOR,
              user.FULL_NAME.as("attendDoctorName"),
              examine.CHECK_IN,
              examine.CHECK_OUT,
              examine.DIAGNOSE,
              examine.STATUS.as("statusId"),
              dataList.LABEL.as("status"))
              .from(examine)
              .leftJoin(medicalDetail)
              .on(examine.EXAMINE_ID.eq(medicalDetail.EXAMINE_ID))
              .leftJoin(user)
              .on(examine.ATTEND_DOCTOR.eq(user.CIF))
              .leftJoin(dataList)
              .on(examine.STATUS.cast(String.class).eq(dataList.DATA_VALUE))
              .where(medicalDetail.MEDICAL_REPORT_ID.eq(medicalReportId))
              .and(dataList.DATA_KEY.eq(EDataList.EXAMINE_STATUS.getKey()))
              .orderBy(examine.CREATED_AT.desc());

      log.info(query.toString());

      return context.select(
              examine.EXAMINE_ID,
              examine.ATTEND_DOCTOR,
              user.FULL_NAME.as("attendDoctorName"),
              examine.CHECK_IN,
              examine.CHECK_OUT,
              examine.DIAGNOSE,
              examine.STATUS.as("statusId"),
              dataList.LABEL.as("status"))
              .from(examine)
              .leftJoin(medicalDetail)
              .on(examine.EXAMINE_ID.eq(medicalDetail.EXAMINE_ID))
              .leftJoin(user)
              .on(examine.ATTEND_DOCTOR.eq(user.CIF))
              .leftJoin(dataList)
              .on(examine.STATUS.cast(String.class).eq(dataList.DATA_VALUE))
              .where(medicalDetail.MEDICAL_REPORT_ID.eq(medicalReportId))
              .and(dataList.DATA_KEY.eq(EDataList.EXAMINE_STATUS.getKey()))
              .orderBy(examine.CREATED_AT.desc())
              .fetchInto(ExamineDetailDto.class)
              .get(0);
    } catch (IndexOutOfBoundsException e) {
      return null;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public ReceiveCustomerListOutput getReceiveCustomerListOutput(GetReceiveCustomerListInput input) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      ExamineInfo examine = ExamineInfo.EXAMINE_INFO;
      MedicalReportDetail medicalDetail = MedicalReportDetail.MEDICAL_REPORT_DETAIL;
      User user = User.USER;
      MedicalReport medicalReport = MedicalReport.MEDICAL_REPORT;
      Customer ctm = Customer.CUSTOMER;
      DataList dataList = DataList.DATA_LIST;

      SelectOnConditionStep query = context.select(
              examine.EXAMINE_ID,
              ctm.FULL_NAME,
              user.FULL_NAME.as("attendDoctor"),
              examine.CHECK_IN,
              examine.CHECK_OUT,
              examine.DIAGNOSE,
              dataList.LABEL.as("status")
      )
              .from(medicalDetail)
              .leftJoin(examine)
              .on(medicalDetail.EXAMINE_ID.eq(examine.EXAMINE_ID))
              .leftJoin(user)
              .on(examine.ATTEND_DOCTOR.eq(user.CIF))
              .leftJoin(medicalReport)
              .on(medicalDetail.MEDICAL_REPORT_ID.eq(medicalReport.MEDICAL_REPORT_ID))
              .leftJoin(ctm)
              .on(medicalReport.CIF.eq(ctm.CIF))
              .leftJoin(dataList)
              .on(examine.STATUS.cast(String.class).eq(dataList.DATA_VALUE));

      query.where(dataList.DATA_KEY.eq(EDataList.EXAMINE_STATUS.getKey()));

      if (input.getFromDate() != null && input.getToDate() != null) {
        LocalDateTime fromDate = DateHelper.toStartLocalDateTime(input.getFromDate());
        LocalDateTime toDate = DateHelper.toEndLocalDateTime(input.getToDate());
        query.where(examine.CREATED_AT.between(fromDate, toDate));
      }

      if (input.getSearch() != null) {
        query.where(ctm.FULL_NAME.likeIgnoreCase("%" + input.getSearch() + "%"));
      }

      Integer pageIndex = input.getPage();

      Integer pageCount = Math.round(count(query.asTable()) / input.getLimit());

      if (pageIndex <= 0) {
        pageIndex = 1;
      }

      log.info(query.toString());
      List<ExamineDto> list = query
              .limit(input.getLimit())
              .offset((pageIndex - 1) * input.getLimit())
              .fetchInto(ExamineDto.class);

      return new ReceiveCustomerListOutput()
              .setLimit(input.getLimit())
              .setPage(input.getPage())
              .setPageCount(pageCount)
              .setList(list);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public ExamineDto getLastExamineByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      ExamineInfo examine = ExamineInfo.EXAMINE_INFO;
      return null;
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<ExamineTodayDto> getExamineToday() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      ExamineInfo examine = ExamineInfo.EXAMINE_INFO;
      MedicalReport medicalReport = MedicalReport.MEDICAL_REPORT;
      MedicalReportDetail medicalReportDetail = MedicalReportDetail.MEDICAL_REPORT_DETAIL;
      Customer ctm = Customer.CUSTOMER;

      LocalDateTime fromDate = DateHelper.toStartLocalDateTime(DateHelper.toMilliseconds(LocalDateTime.now()));
      LocalDateTime toDate = DateHelper.toEndLocalDateTime(DateHelper.toMilliseconds(LocalDateTime.now()));
      return context
              .select(
                      examine.EXAMINE_ID,
                      examine.STATUS,
                      ctm.FULL_NAME,
                      ctm.BOD,
                      ctm.GENDER)
              .from(examine)
              .leftJoin(medicalReportDetail)
              .on(examine.EXAMINE_ID.eq(medicalReportDetail.EXAMINE_ID))
              .leftJoin(medicalReport)
              .on(medicalReportDetail.MEDICAL_REPORT_ID.eq(medicalReport.MEDICAL_REPORT_ID))
              .leftJoin(ctm)
              .on(medicalReport.CIF.eq(ctm.CIF))
              .where(examine.CREATED_AT.between(fromDate, toDate))
              .and(examine.STATUS.ne(EExamineStatus.FINISH.getCode()))
              .fetchInto(ExamineTodayDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<ExamineDoneDto> getExamineDoneByCif(String cif) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      ExamineInfo examine = ExamineInfo.EXAMINE_INFO;
      MedicalReport medicalReport = MedicalReport.MEDICAL_REPORT;
      MedicalReportDetail medicalReportDetail = MedicalReportDetail.MEDICAL_REPORT_DETAIL;
      DataList dataList = DataList.DATA_LIST;
      User user = User.USER;

      SelectConditionStep query = context
              .select(
                      dataList.LABEL.as("status"),
                      user.FULL_NAME.as("attendDoctor"),
                      examine.DIAGNOSE,
                      examine.CHECK_OUT)
              .from(examine)
              .leftJoin(medicalReportDetail)
              .on(examine.EXAMINE_ID.eq(medicalReportDetail.EXAMINE_ID))
              .leftJoin(medicalReport)
              .on(medicalReportDetail.MEDICAL_REPORT_ID.eq(medicalReport.MEDICAL_REPORT_ID))
              .leftJoin(user)
              .on(examine.ATTEND_DOCTOR.eq(user.CIF))
              .leftJoin(dataList)
              .on(examine.STATUS.cast(String.class).eq(dataList.DATA_VALUE))
              .where(medicalReport.CIF.eq(cif))
              .and(dataList.DATA_KEY.eq(EDataList.EXAMINE_STATUS.getKey()))
              .and(examine.STATUS.eq(EExamineStatus.FINISH.getCode()));

      return query
              .fetchInto(ExamineDoneDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
