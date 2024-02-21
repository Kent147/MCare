package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.pojo.api.input.CreateMedicineInput;
import vn.mcare.system.common.pojo.api.input.CreateServiceInput;
import vn.mcare.system.common.pojo.api.input.GetAllServiceOutput;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.api.input.UpdateMedicineInput;
import vn.mcare.system.common.pojo.api.input.UpdateServiceInput;
import vn.mcare.system.common.pojo.api.output.GetAllMedicineOutput;
import vn.mcare.system.common.pojo.api.output.MedForSaleOutput;
import vn.mcare.system.common.pojo.dto.MedForSaleDto;
import vn.mcare.system.common.pojo.dto.MedicineDto;
import vn.mcare.system.common.pojo.dto.ServiceDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.MedicineRepo;
import vn.mcare.system.repository.intface.ServiceRepo;
import vn.mcare.system.repository.model.tables.records.MedicineRecord;
import vn.mcare.system.repository.model.tables.records.ServiceRecord;
import vn.mcare.system.service.intface.ProductService;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductServiceImpl implements ProductService {
  private final Gson gson;
  private final MedicineRepo medicineRepo;
  private final ServiceRepo serviceRepo;

  @Override
  public RestfulCommonResponse getMed(SearchInput search) {
    try {
      GetAllMedicineOutput output = medicineRepo.getAll(search);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulSuccessResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse saveMed(JsonObject payload) {
    try {
      CreateMedicineInput input = gson.fromJson(payload, CreateMedicineInput.class);

      MedicineRecord record =
              new MedicineRecord()
                      .setMedicineId(Generator.generate())
                      .setMedicineName(input.getMedicineName())
                      .setCreatedAt(LocalDateTime.now())
                      .setSource(input.getSource())
                      .setModifiedAt(LocalDateTime.now());

      medicineRepo.save(record);
      return new RestfulSuccessResponse().setMessages("Save medicine success");
    } catch (SQLException.RecordExisted e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse updateMed(JsonObject payload) {
    try {
      UpdateMedicineInput input = gson.fromJson(payload, UpdateMedicineInput.class);

      medicineRepo.getMedicineById(input.getMedicineId());

      MedicineRecord record =
              new MedicineRecord()
                      .setMedicineId(input.getMedicineId())
                      .setMedicineName(input.getMedicineName())
                      .setSource(input.getSource())
                      .setModifiedAt(LocalDateTime.now());

      medicineRepo.update(record);

      return new RestfulSuccessResponse().setMessages("Update medicine success");
    } catch (SQLException.RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse deleteMed(String medicineId) {
    try {

      medicineRepo.delete(medicineId);

      return new RestfulSuccessResponse().setMessages("Delete medicine success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse findMedByName(String medicineName) {
    try {

      List<MedicineDto> output = medicineRepo.findMedicineByName(medicineName);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse findMedForSell(String medicineName) {
    try {

      List<MedForSaleDto> result = medicineRepo.findMedForSell(medicineName);
      List<MedForSaleOutput> output;

      output = result
              .stream()
              .collect(
                      Collectors.groupingBy(MedForSaleDto::getMedicineId))
              .values()
              .stream()
              .map(MedForSaleOutput::new)
              .collect(Collectors.toList());

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getService(SearchInput search) {
    try {
      GetAllServiceOutput output = serviceRepo.getAll(search);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulSuccessResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse saveService(JsonObject payload) {
    try {
      CreateServiceInput input = gson.fromJson(payload, CreateServiceInput.class);

      ServiceRecord record =
              new ServiceRecord()
                      .setServiceId(Generator.generate())
                      .setServiceName(input.getServiceName())
                      .setPrice(input.getPrice())
                      .setDescription(input.getDescription())
                      .setCreatedAt(LocalDateTime.now())
                      .setModifiedAt(LocalDateTime.now());

      serviceRepo.save(record);
      return new RestfulSuccessResponse().setResponse(CodeResponse.CREATED);
    } catch (SQLException.RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse updateService(JsonObject payload) {
    try {
      UpdateServiceInput input = gson.fromJson(payload, UpdateServiceInput.class);

      serviceRepo.getById(input.getServiceId());

      ServiceRecord record =
              new ServiceRecord()
                      .setServiceId(input.getServiceId())
                      .setServiceName(input.getServiceName())
                      .setPrice(input.getPrice())
                      .setDescription(input.getDescription())
                      .setCreatedAt(LocalDateTime.now())
                      .setModifiedAt(LocalDateTime.now());

      serviceRepo.update(record);

      return new RestfulSuccessResponse().setMessages("Update service success");
    } catch (SQLException.RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse deleteService(String serviceId) {
    try {

      serviceRepo.delete(serviceId);

      return new RestfulSuccessResponse().setMessages("Delete service success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getServiceById(String serviceId) {
    try {

      ServiceDto output = serviceRepo.getById(serviceId);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (SQLException.RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse findServiceByName(String serviceName) {
    try {

      List<ServiceDto> output = serviceRepo.searchByName(serviceName);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (SQLException.RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
