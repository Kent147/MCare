package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import lombok.ToString;
import vn.mcare.system.common.annotation.CanNullOrEmpty;

@Getter
@ToString
public class UpdateAdminInput {
    private String adminId;
    private String email;
    private String password;
    private String salt;
    private String fullName;
    @CanNullOrEmpty
    private String description;
}
