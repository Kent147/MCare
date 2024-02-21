package vn.mcare.system.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.Admin;
import vn.mcare.system.common.pojo.api.input.CreateAdminInput;
import vn.mcare.system.common.pojo.api.input.UpdateAdminInput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.service.intface.AdminService;

@RestController
@RequiredArgsConstructor
public class AdminController {

  private final AdminService adminService;
  private final Gson gson;

  @GetMapping(value = Admin.GET)
  public RestfulCommonResponse getAdmins() {
    return adminService.getAdmins();
  }

  @PostMapping(value = Admin.CREATE)
  public RestfulCommonResponse createAdmin(@RequestBody CreateAdminInput input) {
    JsonObject payload = gson.toJsonTree(input).getAsJsonObject();
    return adminService.createAdmin(payload);
  }

  @PutMapping(value = Admin.UPDATE)
  public RestfulCommonResponse updateAdmin(@RequestBody UpdateAdminInput input) {
    JsonObject payload = gson.toJsonTree(input).getAsJsonObject();
    return adminService.updateAdmin(payload);
  }

  @DeleteMapping(value = Admin.DELETE)
  public RestfulCommonResponse delete(@PathVariable("id") String adminId) {
    return adminService.deleteAdmin(adminId);
  }
}
