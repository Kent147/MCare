package vn.mcare.system.controller;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.mcare.system.common.constant.ServerURL.User;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.helper.validation.ValidationLayer;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.AuthorService;
import vn.mcare.system.service.intface.UserService;

@Slf4j
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

  private final UserService userService;
  private final AuthorService authorService;
  private final ValidationLayer validationLayer;

  @PostMapping(value = User.USER_LOGIN)
  public RestfulCommonResponse login(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputUserLogin(payload);
      return authorService.userLogin(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PostMapping(value = User.USER_LOGOUT)
  public RestfulCommonResponse logout(@RequestBody JsonObject payload) {
    return authorService.userLogout(payload.get("cif").getAsString());
  }

  @PostMapping(value = User.USER_RESET_PASSWORD)
  public RestfulCommonResponse resetPassword(@RequestBody JsonObject payload) {
    return authorService.changePassword(payload);
  }

  @GetMapping(value = User.GET)
  public RestfulCommonResponse display() {
    return userService.fetchAll();
  }

  @PostMapping(value = User.CREATE)
  public RestfulCommonResponse create(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputCreateUser(payload);
      return userService.save(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @PutMapping(value = User.UPDATE)
  public RestfulCommonResponse update(@RequestBody JsonObject payload) {
    try {
      validationLayer.checkInputUpdateUser(payload);
      return userService.update(payload);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @DeleteMapping(value = User.DELETE)
  public RestfulCommonResponse delete(@PathVariable("id") String userId, @RequestHeader("cif") String cif) {
    try {
      if (cif.equals(userId)) {
        throw new ValidationException("Can't delete your self");
      }
      return userService.delete(userId);
    } catch (ValidationException e) {
      log.error(e.getMessage());
      return new RestfulFailureResponse();
    }
  }

  @GetMapping(value = User.USER_VALID_EMAIL)
  public RestfulCommonResponse checkValidEmail(@RequestParam(value = "v") String email) {
    return userService.checkValidEmail(email);
  }

  @GetMapping(value = User.USER_VALID_CARDID)
  public RestfulCommonResponse checkValidCardId(@RequestParam(value = "v") String cardId) {
    return userService.checkValidCardId(cardId);
  }
}
