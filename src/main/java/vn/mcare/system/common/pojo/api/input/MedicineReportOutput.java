package vn.mcare.system.common.pojo.api.input;

import lombok.Data;

@Data
public class MedicineReportOutput {

  private String cif;
  private String fullName;
  private Boolean gender;
  private Long bod;
  private Double bloodSugar;
  private Double bloodPressureMax;
  private Double bloodPressureMin;
  private Double cholesterol;
  private Double temperature;
  private Double height;
  private Double weight;

}
