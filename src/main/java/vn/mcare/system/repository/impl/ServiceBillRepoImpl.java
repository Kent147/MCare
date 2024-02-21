package vn.mcare.system.repository.impl;

import org.springframework.stereotype.Repository;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.ServiceBillRepo;
import vn.mcare.system.repository.model.tables.ServiceBill;
import vn.mcare.system.repository.model.tables.records.ServiceBillRecord;

@Repository
public class ServiceBillRepoImpl extends BaseRepoImpl<String, ServiceBillRecord, ServiceBill> implements ServiceBillRepo {
}
