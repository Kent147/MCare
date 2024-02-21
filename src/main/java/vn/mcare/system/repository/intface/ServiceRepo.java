package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.api.input.GetAllServiceOutput;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.dto.ServiceDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.ServiceRecord;

public interface ServiceRepo extends IBaseRepo<String, ServiceRecord> {
  GetAllServiceOutput getAll(SearchInput searchInput);

  ServiceDto getById(String serviceId);

  List<ServiceDto> searchByName(String serviceName);
}
