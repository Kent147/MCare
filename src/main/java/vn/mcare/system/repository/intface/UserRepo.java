package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.api.output.GetAllCustomerOutput;
import vn.mcare.system.common.pojo.api.output.GetAllUserOutput;
import vn.mcare.system.common.pojo.dto.UserDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.UserRecord;

public interface UserRepo extends IBaseRepo<String, UserRecord> {

  List<GetAllUserOutput> getAll();

  void checkValidUser(String email);

  void checkValidCardId(String cardId);

  UserDto getUser(String userId);

  UserDto getUserByEmail(String email);

  UserDto getUserByCif(String cif);

  void changePasswordByCif(String cif, String hash, String salt);

}
