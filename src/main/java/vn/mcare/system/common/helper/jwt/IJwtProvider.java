package vn.mcare.system.common.helper.jwt;

public interface IJwtProvider {
  String genJwt(String payload);
}
