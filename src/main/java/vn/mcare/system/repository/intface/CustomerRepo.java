package vn.mcare.system.repository.intface;

import java.util.List;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.api.output.CustomerDetailOutput;
import vn.mcare.system.common.pojo.api.output.GetAllCustomerOutput;
import vn.mcare.system.common.pojo.api.output.GetCustomerOutput;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.CustomerRecord;

@Component
public interface CustomerRepo extends IBaseRepo<String, CustomerRecord> {

  GetAllCustomerOutput getAll(SearchInput searchInput);

  List<GetCustomerOutput> SearchByName(String customerName);

  void checkValidCardId(String cardId);

  GetCustomerOutput getByCif(String cif);
}
