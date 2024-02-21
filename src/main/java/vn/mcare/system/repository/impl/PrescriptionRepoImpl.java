package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.jooq.SelectHavingStep;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EDataList;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.dto.PrescriptionDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.PrescriptionRepo;
import vn.mcare.system.repository.model.tables.DataList;
import vn.mcare.system.repository.model.tables.Inventory;
import vn.mcare.system.repository.model.tables.Medicine;
import vn.mcare.system.repository.model.tables.Prescription;
import vn.mcare.system.repository.model.tables.records.PrescriptionRecord;

@Slf4j
@Repository
public class PrescriptionRepoImpl extends BaseRepoImpl<Integer, PrescriptionRecord, Prescription> implements PrescriptionRepo {
  @Override
  public List<PrescriptionDto> getByExamineId(String examineId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      Prescription pre = Prescription.PRESCRIPTION;
      Medicine med = Medicine.MEDICINE;
      DataList dataList = DataList.DATA_LIST;
      Inventory inv = Inventory.INVENTORY;

      SelectHavingStep query = context.select(
              pre.ID,
              med.MEDICINE_NAME,
              pre.MEDICINE_ID,
              pre.AMOUNT,
              dataList.LABEL.as("calUnit"),
              pre.CAL_UNIT.as("calUnitId"),
              inv.IMPORT_PRICE,
              inv.SELL_PRICE,
              pre.CREATED_AT)
              .from(pre)
              .leftJoin(med)
              .on(pre.MEDICINE_ID.eq(med.MEDICINE_ID))
              .leftJoin(dataList)
              .on(pre.CAL_UNIT.cast(String.class).eq(dataList.DATA_VALUE))
              .leftJoin(inv)
              .on(pre.MEDICINE_ID.eq(inv.MEDICINE_ID))
              .where(pre.EXAMINE_ID.eq(examineId))
              .and(dataList.DATA_KEY.eq(EDataList.CAL_UNIT.getKey()))
              .groupBy(med.MEDICINE_ID);
      log.info(query.toString());
      return query
              .fetchInto(PrescriptionDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
