package vn.mcare.system.service.intface;

import org.springframework.web.multipart.MultipartFile;
import vn.mcare.system.common.pojo.info.RestfulCommonResponse;

public interface FileService {

  RestfulCommonResponse upload(String bucket, MultipartFile file);

  RestfulCommonResponse uploads(String bucket, MultipartFile[] files);
}
