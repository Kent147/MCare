package vn.mcare.system.repository.intface;

import java.util.List;
import vn.mcare.system.common.pojo.dto.DistrictDto;
import vn.mcare.system.common.pojo.dto.DoctorDto;
import vn.mcare.system.common.pojo.dto.ProvinceDto;
import vn.mcare.system.common.pojo.dto.WardDto;
import vn.mcare.system.repository.IBaseRepo;
import vn.mcare.system.repository.model.tables.records.ProvinceRecord;

public interface CommonRepository extends IBaseRepo<Integer, ProvinceRecord> {

  List<ProvinceDto> getProvinces();

  List<DistrictDto> getDistricts(Integer provinceId);

  List<WardDto> getWards(Integer districtId);

  List<DoctorDto> getDoctor();
}
