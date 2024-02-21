package vn.mcare.system.repository.intface;

import vn.mcare.system.common.pojo.api.input.GetMedBillInput;
import vn.mcare.system.common.pojo.api.output.GetMedBillOutput;
import vn.mcare.system.common.pojo.api.output.GetServiceBillOutput;
import vn.mcare.system.common.pojo.dto.MedBillDto;
import vn.mcare.system.common.pojo.dto.ServiceBillDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.BillRecord;

public interface BillRepo extends IBaseRepo<String, BillRecord> {

  GetMedBillOutput getMedBill(GetMedBillInput input);

  MedBillDto getMedBillDetail(String billId);

  GetServiceBillOutput getServiceBill(GetMedBillInput input);

  ServiceBillDto getServiceBillDetail(String billId);
}
