package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.pojo.dto.DataListDto;
import vn.mcare.system.common.pojo.dto.DistrictDto;
import vn.mcare.system.common.pojo.dto.DoctorDto;
import vn.mcare.system.common.pojo.dto.ProvinceDto;
import vn.mcare.system.common.pojo.dto.WardDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.repository.intface.CommonRepository;
import vn.mcare.system.repository.intface.DataListRepo;
import vn.mcare.system.service.intface.CommonService;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommonServiceImpl implements CommonService {

  private final DataListRepo dataListRepo;
  private final CommonRepository commonRepository;
  private final Gson gson;

  @Override
  public RestfulCommonResponse getDataListByKey(String dataKey) {
    try {

      List<DataListDto> output = dataListRepo.getDataListByKey(dataKey);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getProvinces() {
    try {

      List<ProvinceDto> output = commonRepository.getProvinces();

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getDistrict(Integer provinceId) {
    try {

      List<DistrictDto> output = commonRepository.getDistricts(provinceId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getWard(Integer districtId) {
    try {

      List<WardDto> output = commonRepository.getWards(districtId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getDoctor() {
    try {

      List<DoctorDto> output = commonRepository.getDoctor();

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
