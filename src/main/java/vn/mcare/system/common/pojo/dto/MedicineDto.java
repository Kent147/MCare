package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedicineDto {
  private String medicineId;
  private String medicineName;
  private String source;
  private String sourceName;
  private Long createdAt;
  private Long modifiedAt;
}
