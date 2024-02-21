package vn.mcare.system.repository.intface;

import vn.mcare.system.common.pojo.api.input.ChangePasswordInput;
import vn.mcare.system.common.pojo.dto.UserTokenDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.UserTokenRecord;

public interface UserTokenRepo extends IBaseRepo<Integer, UserTokenRecord> {

  UserTokenDto getByToken(String token);

  void setFalseTokenByCif(String cif);

}
