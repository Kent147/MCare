package vn.mcare.system.common.pojo.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class HealthMaintenanceDto {

  private Integer id;
  private String examineId;
  private Double bloodPressureMin;
  private Double bloodPressureMax;
  private Double bloodSugar;
  private Double cholesterol;
  private Double temperature;
  private Double weight;
  private Double height;
  private Long createdAt;
  private Long modifiedAt;
}
