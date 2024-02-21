package vn.mcare.system.repository.impl;

import org.springframework.stereotype.Repository;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.MedicalReportDetailRepo;
import vn.mcare.system.repository.model.tables.MedicalReportDetail;
import vn.mcare.system.repository.model.tables.records.MedicalReportDetailRecord;

@Repository
public class MedicalReportDetailRepoImpl extends BaseRepoImpl<Integer, MedicalReportDetailRecord, MedicalReportDetail> implements MedicalReportDetailRepo {
}
