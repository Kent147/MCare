package vn.mcare.system.controller;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vn.mcare.system.common.constant.ServerURL;
import vn.mcare.system.common.exception.CommonExceptions.ValidationException;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;
import vn.mcare.system.common.pojo.info.RestfulFailureResponse;
import vn.mcare.system.service.intface.FileService;

@Slf4j
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UploadController {

    private final FileService fileService;

    @PostMapping(ServerURL.MCare.MCARE_UPLOAD)
    public RestfulCommonResponse upload(HttpServletRequest request,
                                        @RequestParam("file") MultipartFile file) {
        try {
            String bucket = request.getParameter("bucket");
            if (bucket == null) {
                return new RestfulFailureResponse().setMessages("bucket is null or empty");
            }
            if (bucket.isEmpty()) {
                return new RestfulFailureResponse().setMessages("bucket is null or empty");
            }

            return fileService.upload(bucket, file);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new RestfulFailureResponse();
        }

    }

    @PostMapping(ServerURL.MCare.MCARE_UPLOAD_MULTI_FILES)
    public RestfulCommonResponse uploadMultiFiles(HttpServletRequest request,
                                                  @RequestParam("files") MultipartFile[] files) {
        try {
            String bucket = request.getParameter("bucket");
            if (bucket == null) {
                return new RestfulFailureResponse().setMessages("bucket is null or empty");
            }
            if (bucket.isEmpty()) {
                return new RestfulFailureResponse().setMessages("bucket is null or empty");
            }

            return fileService.uploads(bucket, files);
        } catch (ValidationException e) {
            log.error(e.getMessage());
            return new RestfulFailureResponse();
        }

    }
}
