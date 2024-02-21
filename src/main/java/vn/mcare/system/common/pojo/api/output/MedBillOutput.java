package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedBillOutput {
    private String billId;
    private Double subPrice;
    private Boolean isVat;
    private Double totalPrice;
    private String customerId;
    private String customerName;
    private String createBy;
    private String description;
    private Long billDate;
}
