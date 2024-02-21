package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.adapter.MailAdapter;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.exception.SQLException;
import vn.mcare.system.common.helper.ContentFormatHelper;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.helper.TwilioHelper;
import vn.mcare.system.common.pojo.api.input.CreateCustomerInput;
import vn.mcare.system.common.pojo.api.input.RegisterExaminationInput;
import vn.mcare.system.common.pojo.api.input.SearchInput;
import vn.mcare.system.common.pojo.api.input.UpdateCustomerInput;
import vn.mcare.system.common.pojo.api.output.GetAllCustomerOutput;
import vn.mcare.system.common.pojo.api.output.GetCustomerOutput;
import vn.mcare.system.common.pojo.api.output.RegisterExaminationOutput;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.common.tool.Generator;
import vn.mcare.system.repository.intface.CustomerRepo;
import vn.mcare.system.repository.intface.MedicalReportRepo;
import vn.mcare.system.repository.intface.RegisterExaminationRepo;
import vn.mcare.system.repository.model.tables.records.CustomerRecord;
import vn.mcare.system.repository.model.tables.records.MedicalReportRecord;
import vn.mcare.system.repository.model.tables.records.RegisterExaminationRecord;
import vn.mcare.system.service.intface.CustomerService;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerServiceImpl implements CustomerService {

  private final Gson gson;
  private final RegisterExaminationRepo registerExaminationRepo;
  private final MailAdapter mailAdapter;
  private final CustomerRepo customerRepo;
  private final MedicalReportRepo medicalReportRepo;

  @Override
  public RestfulCommonResponse registerExamination(JsonObject payload) {
    try {
      RegisterExaminationInput input = gson.fromJson(payload, RegisterExaminationInput.class);
      RegisterExaminationRecord record = new RegisterExaminationRecord();
      String randomNumber = Generator.genRandomNum();

      while (registerExaminationRepo.checkExistRandomNumber(randomNumber)) {
        randomNumber = Generator.genRandomNum();
      }

      LocalDateTime dateTime = registerExaminationRepo.getNextDate();

      record
              .setRandomNumber(randomNumber)
              .setFullName(input.getFullName())
              .setEmail(input.getEmail())
              .setPhone(input.getPhone())
              .setStatus(0)
              .setFromDate(dateTime)
              .setToDate(dateTime.plusMinutes(30))
              .setCreatedAt(LocalDateTime.now())
              .setModifiedAt(LocalDateTime.now());

      registerExaminationRepo.save(record);

      RegisterExaminationOutput output = new RegisterExaminationOutput();
      output
              .setFullName(record.getFullName())
              .setEmail(record.getEmail())
              .setPhone(record.getPhone())
              .setRandomNumber(record.getRandomNumber())
              .setFromDate(DateHelper.toMilliseconds(record.getFromDate()))
              .setToDate(DateHelper.toMilliseconds(record.getToDate()));

      String content = ContentFormatHelper.registerExamination(output);
      TwilioHelper.sendSMS("+84978387250", content);
      mailAdapter.sendMail(
              input.getEmail(), null, "ĐĂNG KÝ KHÁM BỆNH MCARE THÀNH CÔNG", content);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getAllCustomer(SearchInput searchInput) {
    try {
      GetAllCustomerOutput output = customerRepo.getAll(searchInput);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse getByCif(String cif) {
    try {
      GetCustomerOutput output = customerRepo.getByCif(cif);
      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse save(JsonObject payload) {
    try {
      CreateCustomerInput input = gson.fromJson(payload, CreateCustomerInput.class);
      customerRepo.checkValidCardId(input.getCardId());
      CustomerRecord record =
              new CustomerRecord()
                      .setCif(Generator.generate())
                      .setEmail(input.getEmail())
                      .setFullName(input.getFullName())
                      .setGender(input.getGender())
                      .setBod(DateHelper.toLocalDate(input.getBod()))
                      .setCardId(input.getCardId())
                      .setNation(input.getNation())
                      .setPhone(input.getPhone())
                      .setCareer(input.getCareer())
                      .setStreet(input.getStreet())
                      .setWard(input.getWard())
                      .setDistrict(input.getDistrict())
                      .setProvince(input.getProvince())
                      .setCreatedAt(LocalDateTime.now())
                      .setModifiedAt(LocalDateTime.now());

      MedicalReportRecord medicalReportRecord =
              new MedicalReportRecord()
                      .setMedicalReportId(Generator.generate())
                      .setCif(record.getCif())
                      .setCreatedAt(LocalDateTime.now())
                      .setModifiedAt(LocalDateTime.now());

      customerRepo.save(record);
      medicalReportRepo.save(medicalReportRecord);

      return new RestfulSuccessResponse().setMessages("Save customer success");
    } catch (SQLException.RecordExisted e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse update(JsonElement payload) {
    try {
      UpdateCustomerInput input = gson.fromJson(payload, UpdateCustomerInput.class);
      CustomerRecord record =
              new CustomerRecord()
                      .setCif(input.getCif())
                      .setFullName(input.getFullName())
                      .setGender(input.getGender())
                      .setBod(DateHelper.toLocalDate(input.getBod()))
                      .setCardId(input.getCardId())
                      .setNation(input.getNation())
                      .setCareer(input.getCareer())
                      .setPhone(input.getPhone())
                      .setStreet(input.getStreet())
                      .setWard(input.getWard())
                      .setDistrict(input.getDistrict())
                      .setProvince(input.getProvince())
                      .setModifiedAt(LocalDateTime.now());

      customerRepo.update(record);
      return new RestfulSuccessResponse().setMessages("Update customer success");
    } catch (SQLException.RecordNotFound e) {
      return new RestfulFailureResponse().setMessages(e.getMessage());
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse delete(String cif) {
    try {
      customerRepo.delete(cif);
      return new RestfulSuccessResponse().setMessages("Delete customer success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }

  @Override
  public RestfulCommonResponse searchCustomerByFullName(String customeName) {
    try {
      List<GetCustomerOutput> output = customerRepo.SearchByName(customeName);

      return new RestfulSuccessResponse().setData(gson.toJsonTree(output));
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
