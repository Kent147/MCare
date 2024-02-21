package vn.mcare.system.common.pojo.api.output;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.experimental.Accessors;
import vn.mcare.system.common.pojo.dto.CalUnitForSellDto;
import vn.mcare.system.common.pojo.dto.MedForSaleDto;

@Data
@Accessors(chain = true)
public class MedForSaleOutput {
  private String inventoryId;
  private String medicineId;
  private String medicineName;
  private List<CalUnitForSellDto> calUnits = new ArrayList<>();

  public MedForSaleOutput(List<MedForSaleDto> dtos) {
    MedForSaleDto dto = dtos.get(0);
    this.inventoryId = dto.getInventoryId();
    this.medicineId = dto.getMedicineId();
    this.medicineName = dto.getMedicineName();

    dtos.forEach(item -> {
      this.calUnits.add(new CalUnitForSellDto()
              .setCalUnit(item.getCalUnit())
              .setCalUnitName(item.getCalUnitName())
              .setImportPrice(item.getImportPrice())
              .setSellPrice(item.getSellPrice())
              .setAmount(item.getAmount()));
    });
  }
}
