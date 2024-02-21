package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.api.output.GetAllMedicineOutput;
import vn.mcare.system.common.pojo.dto.MedForSaleDto;
import vn.mcare.system.common.pojo.dto.MedicineDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.MedicineRecord;

public interface MedicineRepo extends IBaseRepo<String, MedicineRecord> {
  GetAllMedicineOutput getAll(SearchInput searchInput);

  void checkValidMedicine(String medicineId);

  MedicineDto getMedicineById(String medicineId);

  List<MedicineDto> findMedicineByName(String name);

  List<MedForSaleDto> findMedForSell(String name);

}
