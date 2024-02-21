package vn.mcare.system.common.pojo.api.output;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class MedicalHistoryDetail {

    private Integer id;
    private String examineId;
    private String pathological;
    private Integer status;
    private Integer note;
    private Long createdAt;

}
