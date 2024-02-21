package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.dto.DataListDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.DataListRecord;

public interface DataListRepo extends IBaseRepo<Integer, DataListRecord> {

  List<DataListDto> getDataListByKey(String key);

}
