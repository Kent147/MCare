package vn.mcare.system.repository.impl;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.enums.EUserRole;
import vn.mcare.system.common.context.AutoCloseableDSLContext;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.dto.DistrictDto;
import vn.mcare.system.common.pojo.dto.DoctorDto;
import vn.mcare.system.common.pojo.dto.ProvinceDto;
import vn.mcare.system.common.pojo.dto.WardDto;
import vn.mcare.system.repository.BaseRepoImpl;
import vn.mcare.system.repository.intface.CommonRepository;
import vn.mcare.system.repository.model.tables.District;
import vn.mcare.system.repository.model.tables.Province;
import vn.mcare.system.repository.model.tables.User;
import vn.mcare.system.repository.model.tables.Ward;
import vn.mcare.system.repository.model.tables.records.ProvinceRecord;

@Repository
@Slf4j
public class CommonRepositoryImpl extends BaseRepoImpl<Integer, ProvinceRecord, Province> implements
        CommonRepository {

  @Override
  public List<ProvinceDto> getProvinces() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
              .selectFrom(Province.PROVINCE)
              .fetchInto(ProvinceDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<DistrictDto> getDistricts(Integer provinceId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
              .selectFrom(District.DISTRICT)
              .where(District.DISTRICT.PROVINCE_ID.eq(provinceId))
              .fetchInto(DistrictDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<WardDto> getWards(Integer districtId) {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      return context
              .selectFrom(Ward.WARD)
              .where(Ward.WARD.DISTRICT_ID.eq(districtId))
              .fetchInto(WardDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }

  @Override
  public List<DoctorDto> getDoctor() {
    try (AutoCloseableDSLContext context = getAutoCloseableDSLContext()) {
      User user = User.USER;

      return context
              .selectFrom(user)
              .where(user.ROLE.eq(EUserRole.DOCTOR.getCode()))
              .fetchInto(DoctorDto.class);
    } catch (Exception e) {
      log.error(ErrorMessage.SQL_ERROR.getMessage(), e);
      throw new SQLException.SQLError(ErrorMessage.SQL_ERROR.getMessage());
    }
  }
}
