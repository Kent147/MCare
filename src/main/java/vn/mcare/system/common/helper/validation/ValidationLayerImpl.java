package vn.mcare.system.common.helper.validation;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.constant.enums.EExamineStatus;
import vn.mcare.system.common.exception.CommonExceptions;
import vn.mcare.system.common.exception.CommonExceptions.ValidationError;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.pojo.api.input.CreateCustomerInput;
import vn.mcare.system.common.pojo.api.input.CreateExamineInput;
import vn.mcare.system.common.pojo.api.input.CreateHealthMaintenanceInput;
import vn.mcare.system.common.pojo.api.input.CreateMedicineBillInput;
import vn.mcare.system.common.pojo.api.input.CreateMedicineInput;
import vn.mcare.system.common.pojo.api.input.CreatePrescriptionInput;
import vn.mcare.system.common.pojo.api.input.CreateServiceBillInput;
import vn.mcare.system.common.pojo.api.input.CreateServiceInput;
import vn.mcare.system.common.pojo.api.input.CreateUserInput;
import vn.mcare.system.common.pojo.api.input.GetMedBillInput;
import vn.mcare.system.common.pojo.api.input.GetReceiveCustomerListInput;
import vn.mcare.system.common.pojo.api.input.ReceiveCustomerInput;
import vn.mcare.system.common.pojo.api.input.RegChangeStatusInput;
import vn.mcare.system.common.pojo.api.input.RegisterExaminationInput;
import vn.mcare.system.common.pojo.api.input.StatisticInput;
import vn.mcare.system.common.pojo.api.input.UpdateCustomerInput;
import vn.mcare.system.common.pojo.api.input.UpdateHealthMaintenanceInput;
import vn.mcare.system.common.pojo.api.input.UpdateMedicineInput;
import vn.mcare.system.common.pojo.api.input.UpdatePrescriptionInput;
import vn.mcare.system.common.pojo.api.input.UpdateServiceInput;
import vn.mcare.system.common.pojo.api.input.UpdateUserInput;
import vn.mcare.system.common.pojo.api.input.UserLoginInput;
import vn.mcare.system.common.tool.validation.Validation;

@Component
@RequiredArgsConstructor
@Slf4j
public class ValidationLayerImpl implements ValidationLayer {

  private final Validation validation;
  private final Gson gson;

  @Override
  public void checkInputCreateUser(JsonElement payload) {
    try {
      CreateUserInput input = gson.fromJson(payload, CreateUserInput.class);
      validation.checkCanNullWithAnnotation(input);
      validation.checkValidEmailAddress(input.getEmail());
      validation.checkValidClearTextPassword(input.getPassword());
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUpdateUser(JsonElement payload) {
    try {
      UpdateUserInput input = gson.fromJson(payload, UpdateUserInput.class);
      validation.checkCanNullWithAnnotation(input);
      validation.checkValidEmailAddress(input.getEmail());
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUserLogin(JsonElement payload) {
    try {
      UserLoginInput input = gson.fromJson(payload, UserLoginInput.class);
      validation.checkCanNullWithAnnotation(input);
      validation.checkValidEmailAddress(input.getEmail());
      validation.checkValidClearTextPassword(input.getPassword());
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateMedicine(JsonElement payload) {
    try {
      CreateMedicineInput input = gson.fromJson(payload, CreateMedicineInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUpdateMedicine(JsonElement payload) {
    try {
      UpdateMedicineInput input = gson.fromJson(payload, UpdateMedicineInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputRegisterExamination(JsonObject payload) {
    try {
      RegisterExaminationInput input = gson.fromJson(payload, RegisterExaminationInput.class);
      validation.checkCanNullWithAnnotation(input);
      validation.checkValidEmailAddress(input.getEmail());
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateCustomer(JsonElement payload) {
    try {
      CreateCustomerInput input = gson.fromJson(payload, CreateCustomerInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUpdateCustomer(JsonElement payload) {
    try {
      UpdateCustomerInput input = gson.fromJson(payload, UpdateCustomerInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUpdateHealthMaintenance(JsonObject payload) {
    try {
      UpdateHealthMaintenanceInput input = gson.fromJson(payload, UpdateHealthMaintenanceInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateHealthMaintenance(JsonObject payload) {
    try {
      CreateHealthMaintenanceInput input = gson.fromJson(payload, CreateHealthMaintenanceInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUpdatePrescription(JsonObject payload) {
    try {
      UpdatePrescriptionInput input = gson.fromJson(payload, UpdatePrescriptionInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreatePrescription(JsonObject payload) {
    try {
      CreatePrescriptionInput input = gson.fromJson(payload, CreatePrescriptionInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateExamine(JsonObject payload) {
    try {
      CreateExamineInput input = gson.fromJson(payload, CreateExamineInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateMedicineBill(JsonObject payload) {
    try {
      CreateMedicineBillInput input = gson.fromJson(payload, CreateMedicineBillInput.class);
      validation.checkCanNullWithAnnotation(input);
      input.getMedicines().remove(input.getMedicines().size() - 1);

      input.getMedicines()
              .forEach(medInventoryDto -> {
                validation.checkCanNullWithAnnotation(medInventoryDto);
              });
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputGetMedBill(JsonObject payload) {
    try {
      GetMedBillInput input = gson.fromJson(payload, GetMedBillInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputUpdateService(JsonObject payload) {
    try {
      UpdateServiceInput input = gson.fromJson(payload, UpdateServiceInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateService(JsonObject payload) {
    try {
      CreateServiceInput input = gson.fromJson(payload, CreateServiceInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputCreateServiceBill(JsonObject payload) {
    try {
      CreateServiceBillInput input = gson.fromJson(payload, CreateServiceBillInput.class);
      validation.checkCanNullWithAnnotation(input);
      input.getServices().remove(input.getServices().size() - 1);

      input.getServices().forEach(service -> {
        validation.checkCanNullWithAnnotation(service);
      });
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputStatistic(JsonObject payload) {
    try {
      StatisticInput input = gson.fromJson(payload, StatisticInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputChangeRegStatus(JsonObject payload) {
    try {
      RegChangeStatusInput input = gson.fromJson(payload, RegChangeStatusInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputChangeExamineStatus(JsonObject payload) {
    try {
      ChangeExamineStatusInput input = gson.fromJson(payload, ChangeExamineStatusInput.class);
      validation.checkCanNullWithAnnotation(input);

      Boolean valid = EExamineStatus.isValid(input.getStatus());

      if (!valid) {
        throw new CommonExceptions.ValidationError("Invalid status");
      }

    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputGetReceiveCustomerList(JsonObject payload) {
    try {
      GetReceiveCustomerListInput input = gson.fromJson(payload, GetReceiveCustomerListInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

  @Override
  public void checkInputReceiveCustomer(JsonObject payload) {
    try {
      ReceiveCustomerInput input = gson.fromJson(payload, ReceiveCustomerInput.class);
      validation.checkCanNullWithAnnotation(input);
    } catch (ValidationError e) {
      throw new ValidationException(e.getMessage());
    }
  }

}
