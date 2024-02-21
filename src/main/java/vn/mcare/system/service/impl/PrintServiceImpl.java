package vn.mcare.system.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.helper.DateHelper;
import vn.mcare.system.common.helper.PdfHelper;
import vn.mcare.system.common.pojo.dto.PrescriptionDto;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.repository.intface.PrescriptionRepo;
import vn.mcare.system.service.intface.PrintService;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PrintServiceImpl implements PrintService {

  private final PrescriptionRepo prescriptionRepo;

  @Override
  public RestfulCommonResponse printPrescription(String examineId) {
    try {
      List<PrescriptionDto> prescriptions = prescriptionRepo.getByExamineId(examineId);

      //PdfHelper.createPdf("prescription.pdf", "Thang", "Minh Minh Thang", "Nam", "Yo hello", "24/11/2000", "not have description bro");

      DateHelper.toMilliseconds(LocalDateTime.now());
      String fileName = DateHelper.toMilliseconds(LocalDateTime.now()) + ".prescriptionList.pdf";
      PdfHelper.createPdf(fileName, prescriptions);
      fileName = System.getProperty("user.dir") + "\\" + fileName;

      return new RestfulSuccessResponse().setMessages(fileName);
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulSuccessResponse().setResponse(CodeResponse.INTERNAL_SERVER);
    }
  }
}
