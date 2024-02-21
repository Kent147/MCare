package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EBillType;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.pojo.api.input.CreateMedicineBillInput;
import vn.mcare.system.common.pojo.api.input.CreateServiceBillInput;
import vn.mcare.system.common.pojo.api.input.GetMedBillInput;
import vn.mcare.system.common.pojo.api.output.GetMedBillOutput;
import vn.mcare.system.common.pojo.api.output.GetServiceBillOutput;
import vn.mcare.system.common.pojo.dto.MedBillDto;
import vn.mcare.system.common.pojo.dto.PrescriptionDto;
import vn.mcare.system.common.pojo.dto.ServiceBillDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.BillRepo;
import vn.mcare.system.repository.intface.MedicineBillRepo;
import vn.mcare.system.repository.intface.PrescriptionRepo;
import vn.mcare.system.repository.intface.ServiceBillRepo;
import vn.mcare.system.repository.model.tables.records.BillRecord;
import vn.mcare.system.repository.model.tables.records.MedicineBillRecord;
import vn.mcare.system.repository.model.tables.records.ServiceBillRecord;
import vn.mcare.system.service.intface.BillService;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BillServiceImpl implements BillService {

  private final Gson gson;
  private final BillRepo billRepo;
  private final MedicineBillRepo medicineBillRepo;
  private final PrescriptionRepo prescriptionRepo;
  private final ServiceBillRepo serviceBillRepo;

  @Override
  public RestfulCommonResponse getMedBill(JsonObject payload) {
    try {
      GetMedBillInput input = gson.fromJson(payload, GetMedBillInput.class);
      GetMedBillOutput output = billRepo.getMedBill(input);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse createMedicineBill(JsonObject payload) {
    try {
      CreateMedicineBillInput input = gson.fromJson(payload, CreateMedicineBillInput.class);

      String billId = Generator.generate();

      BillRecord billRecord = new BillRecord();
      billRecord
              .setBillId(billId)
              .setBillDate(DateHelper.toLocalDateTime(input.getBillDate()))
              .setBillType(EBillType.MEDICINE_EXPORT.getCode())
              .setIsVat(input.getIsVat())
              .setVatRate(input.getVatRate())
              .setSubPrice(input.getSubPrice())
              .setVatPrice(input.getVatPrice())
              .setTotalPrice(input.getTotalPrice())
              .setCreateBy(input.getCreatedBy())
              .setCustomerId(input.getCustomerId());

      input.getMedicines().remove(input.getMedicines().size() - 1);
      input.getMedicines().forEach(med -> {
        MedicineBillRecord medBillRecord = new MedicineBillRecord();
        medBillRecord
                .setBillId(billId)
                .setMedicineId(med.getMedicineId())
                .setInventoryId(med.getInventoryId())
                .setAmount(med.getAmount())
                .setCalUnit(med.getCalUnit())
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now());

        medicineBillRepo.save(medBillRecord);
      });

      billRepo.save(billRecord);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse genMedicineBill(String examineId) {
    try {
      List<PrescriptionDto> output = prescriptionRepo.getByExamineId(examineId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getMedBillDetail(String billId) {
    try {

      MedBillDto output = billRepo.getMedBillDetail(billId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getServiceBill(JsonObject payload) {
    try {
      GetMedBillInput input = gson.fromJson(payload, GetMedBillInput.class);
      GetServiceBillOutput output = billRepo.getServiceBill(input);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getServiceBillDetail(String billId) {
    try {

      ServiceBillDto output = billRepo.getServiceBillDetail(billId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse createServiceBill(JsonObject payload) {
    try {
      CreateServiceBillInput input = gson.fromJson(payload, CreateServiceBillInput.class);

      String billId = Generator.generate();

      BillRecord billRecord = new BillRecord();
      billRecord
              .setBillId(billId)
              .setBillDate(DateHelper.toLocalDateTime(input.getBillDate()))
              .setBillType(EBillType.SERVICE_EXPORT.getCode())
              .setIsVat(input.getIsVat())
              .setVatRate(input.getVatRate())
              .setSubPrice(input.getTotalPrice())
              .setVatPrice(input.getVatPrice())
              .setTotalPrice(input.getTotalPrice())
              .setCreateBy(input.getCreatedBy())
              .setCustomerId(input.getCustomerId());

      input.getServices().remove(input.getServices().size() - 1);
      input.getServices().forEach(service -> {
        ServiceBillRecord serviceBillRecord = new ServiceBillRecord();
        serviceBillRecord
                .setBillId(billId)
                .setServiceId(service.getServiceId())
                .setFromDate(LocalDate.now())
                .setToDate(LocalDate.now())
                .setQuantity(service.getAmount())
                .setCreatedAt(LocalDateTime.now())
                .setModifiedAt(LocalDateTime.now());

        serviceBillRepo.save(serviceBillRecord);
      });

      billRepo.save(billRecord);

      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
