package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SelectConditionStep;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EBillType;
import vn.mcare.system.common.constant.enums.EDataList;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.pojo.api.input.GetMedBillInput;
import vn.mcare.system.common.pojo.api.output.GetMedBillOutput;
import vn.mcare.system.common.pojo.api.output.GetServiceBillOutput;
import vn.mcare.system.common.pojo.api.output.MedBillOutput;
import vn.mcare.system.common.pojo.api.output.ServiceBillOutput;
import vn.mcare.system.common.pojo.dto.MedBillDetailDto;
import vn.mcare.system.common.pojo.dto.MedBillDto;
import vn.mcare.system.common.pojo.dto.ServiceBillDetailDto;
import vn.mcare.system.common.pojo.dto.ServiceBillDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.BillRepo;
import vn.mcare.system.repository.model.tables.Bill;
import vn.mcare.system.repository.model.tables.Customer;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.Inventory;
import vn.mcare.system.repository.model.tables.Medicine;
import vn.mcare.system.repository.model.tables.MedicineBill;
import vn.mcare.system.repository.model.tables.Service;
import vn.mcare.system.repository.model.tables.ServiceBill;
import vn.mcare.system.repository.model.tables.records.BillRecord;

@Slf4j
@Repository
public class BillRepoImpl extends BaseRepoImpl<String, BillRecord, Bill> implements BillRepo {

  @Override
  public GetMedBillOutput getMedBill(GetMedBillInput input) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      MedicineBill medBill = MedicineBill.MEDICINE_BILL;
      Customer ctm = Customer.CUSTOMER;

      SelectConditionStep query = context
              .select(
                      bill.BILL_ID,
                      bill.SUB_PRICE,
                      bill.IS_VAT,
                      bill.TOTAL_PRICE,
                      bill.CUSTOMER_ID,
                      ctm.FULL_NAME.as("customerName"),
                      bill.CREATE_BY,
                      bill.DESCRIPTION,
                      bill.BILL_DATE
              )
              .from(medBill)
              .leftJoin(bill)
              .on(medBill.BILL_ID.eq(bill.BILL_ID))
              .leftJoin(ctm)
              .on(bill.CUSTOMER_ID.eq(ctm.CIF))
              .where(bill.BILL_TYPE.eq(EBillType.MEDICINE_EXPORT.getCode()))
              .and(ctm.FULL_NAME.likeIgnoreCase("%" + input.getSearch() + "%"));

      if (input.getIsVat() != null) {
        query = query.and(bill.IS_VAT.eq(input.getIsVat()));
      }

      if (input.getFromDate() != null && input.getToDate() != null) {
        query = query.and(bill.BILL_DATE.between(DateHelper.toStartLocalDateTime(input.getFromDate()), DateHelper.toEndLocalDateTime(input.getToDate())));
      }

      Integer pageIndex = input.getPage();

      if (pageIndex <= 0) {
        pageIndex = 1;
      }

      Integer pageCount = Math.round(count(query.asTable()) / input.getLimit());

      List<MedBillOutput> list = query
              .groupBy(bill.BILL_ID)
              .orderBy(bill.BILL_DATE.desc())
              .limit(input.getLimit())
              .offset((pageIndex - 1) * input.getLimit())
              .fetchInto(MedBillOutput.class);

      return new GetMedBillOutput()
              .setLimit(input.getLimit())
              .setPageCount(pageCount)
              .setPage(input.getPage())
              .setList(list);

    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public MedBillDto getMedBillDetail(String billId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      MedicineBill medBill = MedicineBill.MEDICINE_BILL;
      Medicine med = Medicine.MEDICINE;
      Inventory inv = Inventory.INVENTORY;
      DataList dataList = DataList.DATA_LIST;
      Customer ctm = Customer.CUSTOMER;

      MedBillDto medBillDto = context
              .select(
                      bill.BILL_ID,
                      bill.SUB_PRICE,
                      bill.IS_VAT,
                      bill.VAT_PRICE,
                      bill.TOTAL_PRICE,
                      bill.CUSTOMER_ID,
                      bill.CREATE_BY,
                      bill.DESCRIPTION,
                      medBill.INVENTORY_ID,
                      bill.BILL_DATE,
                      ctm.FULL_NAME.as("customerName")
              )
              .from(bill)
              .leftJoin(medBill)
              .on(bill.BILL_ID.eq(medBill.BILL_ID))
              .leftJoin(ctm)
              .on(bill.CUSTOMER_ID.eq(ctm.CIF))
              .where(bill.BILL_TYPE.eq(EBillType.MEDICINE_EXPORT.getCode()))
              .and(bill.BILL_ID.eq(billId))
              .fetchInto(MedBillDto.class)
              .get(0);

      List<MedBillDetailDto> medBillDetailDtos = context
              .select(
                      medBill.INVENTORY_ID,
                      medBill.MEDICINE_ID,
                      medBill.AMOUNT,
                      dataList.LABEL.as("calUnit"),
                      inv.LOT_ID,
                      med.MEDICINE_NAME)
              .from(medBill)
              .leftJoin(inv)
              .on(medBill.INVENTORY_ID.eq(inv.INVENTORY_ID))
              .and(medBill.MEDICINE_ID.eq(inv.MEDICINE_ID))
              .leftJoin(med)
              .on(inv.MEDICINE_ID.eq(med.MEDICINE_ID))
              .leftJoin(dataList)
              .on(medBill.CAL_UNIT.eq(dataList.DATA_VALUE.cast(Integer.class)))
              .where(medBill.BILL_ID.eq(billId))
              .and(dataList.DATA_KEY.eq(EDataList.CAL_UNIT.getKey()))
              .fetchInto(MedBillDetailDto.class);

      medBillDto.setMedicines(medBillDetailDtos);

      return medBillDto;

    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public GetServiceBillOutput getServiceBill(GetMedBillInput input) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      ServiceBill serviceBill = ServiceBill.SERVICE_BILL;
      Customer ctm = Customer.CUSTOMER;

      SelectConditionStep query = context
              .select(
                      bill.BILL_ID,
                      bill.SUB_PRICE,
                      bill.IS_VAT,
                      bill.TOTAL_PRICE,
                      bill.CUSTOMER_ID,
                      ctm.FULL_NAME.as("customerName"),
                      bill.CREATE_BY,
                      bill.DESCRIPTION,
                      bill.BILL_DATE
              )
              .from(serviceBill)
              .leftJoin(bill)
              .on(serviceBill.BILL_ID.eq(bill.BILL_ID))
              .leftJoin(ctm)
              .on(bill.CUSTOMER_ID.eq(ctm.CIF))
              .where(bill.BILL_TYPE.eq(EBillType.SERVICE_EXPORT.getCode()))
              .and(ctm.FULL_NAME.likeIgnoreCase("%" + input.getSearch() + "%"));

      if (input.getIsVat() != null) {
        query = query.and(bill.IS_VAT.eq(input.getIsVat()));
      }

      if (input.getFromDate() != null && input.getToDate() != null) {
        query = query.and(bill.BILL_DATE.between(DateHelper.toStartLocalDateTime(input.getFromDate()), DateHelper.toEndLocalDateTime(input.getToDate())));
      }

      Integer pageIndex = input.getPage();

      if (pageIndex <= 0) {
        pageIndex = 1;
      }

      Integer pageCount = Math.round(count(query.asTable()) / input.getLimit());


      List<ServiceBillOutput> list = query
              .groupBy(bill.BILL_ID)
              .orderBy(bill.BILL_DATE.desc())
              .limit(input.getLimit())
              .offset((pageIndex - 1) * input.getLimit())
              .fetchInto(ServiceBillOutput.class);

      return new GetServiceBillOutput()
              .setLimit(input.getLimit())
              .setPageCount(pageCount)
              .setPage(input.getPage())
              .setList(list);

    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }

  }

  @Override
  public ServiceBillDto getServiceBillDetail(String billId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Bill bill = Bill.BILL;
      ServiceBill serviceBill = ServiceBill.SERVICE_BILL;
      Service service = Service.SERVICE;
      Customer ctm = Customer.CUSTOMER;

      ServiceBillDto serviceBillDto = context
              .select(
                      bill.BILL_ID,
                      bill.SUB_PRICE,
                      bill.IS_VAT,
                      bill.VAT_PRICE,
                      bill.TOTAL_PRICE,
                      bill.CUSTOMER_ID,
                      bill.CREATE_BY,
                      bill.DESCRIPTION,
                      bill.BILL_DATE,
                      ctm.FULL_NAME.as("customerName")
              )
              .from(bill)
              .leftJoin(serviceBill)
              .on(bill.BILL_ID.eq(serviceBill.BILL_ID))
              .leftJoin(ctm)
              .on(bill.CUSTOMER_ID.eq(ctm.CIF))
              .where(bill.BILL_TYPE.eq(EBillType.SERVICE_EXPORT.getCode()))
              .and(bill.BILL_ID.eq(billId))
              .fetchInto(ServiceBillDto.class)
              .get(0);

      List<ServiceBillDetailDto> serviceBillDetailDtos = context
              .select(
                      serviceBill.QUANTITY,
                      service.SERVICE_ID,
                      service.PRICE,
                      service.SERVICE_NAME)
              .from(serviceBill)
              .leftJoin(service)
              .on(serviceBill.SERVICE_ID.eq(service.SERVICE_ID))
              .where(serviceBill.BILL_ID.eq(billId))
              .fetchInto(ServiceBillDetailDto.class);

      serviceBillDto.setServices(serviceBillDetailDtos);

      return serviceBillDto;

    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
