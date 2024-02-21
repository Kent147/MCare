package vn.mcare.system.service.impl;

import com.google.gson.Gson;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import vn.mcare.system.common.constant.ErrorMessage;
import vn.mcare.system.common.constant.req_resp.CodeResponse;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.common.pojo.info.RestfulSuccessResponse;
import vn.mcare.system.service.intface.FileService;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FileServiceImpl implements FileService {

  private final Gson gson;
  private String uploadDir = "C:\\Users\\minht\\Desktop\\";

  @Override
  public RestfulCommonResponse upload(String bucket, MultipartFile file) {
    try {
      Path dir = Paths.get(uploadDir + bucket);

      if (!Files.isDirectory(dir)) {
        Files.createDirectory(dir);
      }

      Path copyLocation = Paths.get(
          uploadDir + bucket + File.separator + StringUtils
              .cleanPath(file.getOriginalFilename()));
      Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);

      log.info("Save file " + copyLocation + " success!");

      return new RestfulSuccessResponse().setMessages("Upload success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse()
          .setResponse(CodeResponse.INTERNAL_SERVER)
          .setMessages("Upload error");
    }
  }

  @Override
  public RestfulCommonResponse uploads(String bucket, MultipartFile[] files) {
    try {
      Path dir = Paths.get(uploadDir + bucket);

      if (!Files.isDirectory(dir)) {
        Files.createDirectory(dir);
      }

      for (MultipartFile file : files) {
        Path copyLocation = Paths.get(
            uploadDir + bucket + File.separator + StringUtils
                .cleanPath(file.getOriginalFilename()));
        Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        log.info("Save file " + copyLocation + " success!");
      }

      return new RestfulSuccessResponse().setMessages("Upload success");
    } catch (Exception e) {
      log.error(ErrorMessage.UNKNOWN_ERROR.getMessage(), e);
      return new RestfulFailureResponse()
          .setResponse(CodeResponse.INTERNAL_SERVER)
          .setMessages("Upload error");
    }
  }
}
