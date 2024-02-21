package vn.mcare.system.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.MedicineBillRepo;
import vn.mcare.system.repository.model.tables.MedicineBill;
import vn.mcare.system.repository.model.tables.records.MedicineBillRecord;

@Slf4j
@Repository
public class MedicineBillRepoImpl extends BaseRepoImpl<Integer, MedicineBillRecord, MedicineBill> implements MedicineBillRepo {

}
