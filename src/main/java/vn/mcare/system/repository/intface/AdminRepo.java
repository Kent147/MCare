package vn.mcare.system.repository.intface;

import java.util.List;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.pojo.dto.AdminDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.AdminRecord;

@Component
public interface AdminRepo extends IBaseRepo<String, AdminRecord> {

  List<AdminDto> getAll();

  void checkValidAdmin(String cif);

  AdminDto getAdmin(String cif);

}
