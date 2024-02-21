package vn.mcare.system.common.constant;

public interface ServerURL {

  interface MCare {

    String MCARE_UPLOAD = "/mcare/upload";
    String MCARE_UPLOAD_MULTI_FILES = "/mcare/uploadMultiFiles";
  }

  interface Admin {

    String GET = "/admin";
    String CREATE = "/admin/create";
    String UPDATE = "/admin/updateMed";
    String DELETE = "/admin/deleteMed/{id}";
  }

  interface User {

    String GET = "/user";
    String CREATE = "/user/create";
    String UPDATE = "/user/update";
    String DELETE = "/user/delete/{id}";
    String USER_LOGIN = "/user/login";
    String USER_LOGOUT = "/user/logout";
    String USER_RESET_PASSWORD = "/user/change_password";
    String USER_VALID_EMAIL = "/user/valid/email";
    String USER_VALID_CARDID = "/user/valid/cardid";

  }

  interface Medicine {

    String GET = "/medicine";
    String CREATE = "/medicine/create";
    String UPDATE = "/medicine/updateMed";
    String DELETE = "/medicine/deleteMed/{id}";
    String FIND_BY_NAME = "/medicine/{medicineName}";
    String FIND_FOR_SELL = "/medicine/forSell/{medicineName}";
  }

  interface Service {

    String GET = "/service";
    String GET_BY_ID = "/service/{id}";
    String FIND_BY_NAME = "/service/search/{serviceName}";
    String CREATE = "/service/create";
    String UPDATE = "/service/update";
    String DELETE = "/service/delete/{id}";
  }

  interface Customer {

    String REGISTER_EXAMINATION = "/registerExamination";
    String GET = "/customer";
    String GET_BY_CIF = "/customer/{cif}";
    String CREATE = "/customer/create";
    String SEARCH_BY_NAME = "/customer/search/{name}";
    String UPDATE = "/customer/update";
    String DELETE = "/customer/delete/{cif}";
    String REGISTER_CHANGE_STATUS = "register/changeStatus";
    String RECEIVE_REGISTER_EXAMINATION = "register/receive";
  }

  interface Common {

    String GET_DATA_LIST = "/common/dataList/{key}";
    String GET_PROVINCE = "/common/province";
    String GET_DISTRICT = "/common/district/{id}";
    String GET_WARD = "/common/ward/{id}";
    String GET_DOCTOR = "/common/doctor";
  }

  interface MedicalReport {

    String GET_EXAMINE = "/medicalReport/examine/{id}";
    String GET_EXAMINE_DETAIL = "/medicalReport/examine/detail/{id}";
    String CREATE_EXAMINE = "/medicalReport/examine";
    String UPDATE_EXAMINE = "/medicalReport/examine";
    String DELETE_EXAMINE = "/medicalReport/examine";
    String CHANGE_EXAMINE_STATUS = "medicalReport/examine/changeStatus";

    String GET_PRESCRIPTION = "/medicalReport/prescription/{id}";
    String CREATE_PRESCRIPTION = "/medicalReport/prescription";
    String UPDATE_PRESCRIPTION = "/medicalReport/prescription";
    String DELETE_PRESCRIPTION = "/medicalReport/prescription/{id}";

    String GET_HEALTH_MAINTENANCE = "/medicalReport/healthMaintenance/{id}";
    String CREATE_HEALTH_MAINTENANCE = "/medicalReport/healthMaintenance";

    String GET_MEDICAL_HISTORY_BY_CIF = "/medicalReport/medicalHistory/{cif}";
    String DELETE_HEALTH_HISTORY = "/medicalReport/medicalHistory/{id}";
  }

  interface Bill {
    String GET_MEDICINE_BILL = "bill/medicine";
    String GET_MEDICINE_BILL_DETAIL = "bill/medicine/{id}";
    String GEN_MEDICINE_BILL = "bill/medicine/gen/{id}";

    String CREATE_MEDICINE_BILL = "bill/medicine/create";

    String GET_SERVICE_BILL = "bill/service";
    String GET_SERVICE_BILL_DETAIL = "bill/service/{id}";
    String CREATE_SERVICE_BILL = "bill/service/create";
  }

  interface Print {
    String PRINT_PRESCRIPTION = "/print/prescription/{id}";
  }

  interface Statistic {
    String STATISTIC_WEEK = "/statistic/week";
    String STATISTIC_MONTH = "/statistic/month";
    String STATISTIC_YEAR = "/statistic/year";

    String STATISTIC_BEST_SELLERS = "/statistic/bestSellers";
    String STATISTIC_REVENUE = "/statistic/revenue";
    String STATISTIC_BILLS = "/statistic/bills";
  }

  interface Staff {
    String GET_RECEIVE_CUSTOMER_LIST = "/staff/receiveCustomerList";
    String CREATE_RECEIVE_CUSTOMER = "/staff/receive/create";
    String GET_EXAMINE_TODAY = "/staff/examine/today";

  }

}
