package vn.mcare.system.common.pojo.api.input;

import lombok.Getter;
import vn.mcare.system.common.annotation.CanNullOrEmpty;
@Getter
public class CreateAdminInput {
    private String email;
    private String password;
    private String salt;
    private String fullName;
    @CanNullOrEmpty
    private String description;
}
