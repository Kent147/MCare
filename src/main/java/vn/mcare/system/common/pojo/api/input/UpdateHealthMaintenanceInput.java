package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;

@Getter
public class UpdateHealthMaintenanceInput {
  private Integer id;
  private String examineId;
  private Double bloodPressureMin;
  private Double bloodPressureMax;
  private Double bloodSugar;
  private Double cholesterol;
  private Double temperature;
  private Double weight;
  private Double height;
}
