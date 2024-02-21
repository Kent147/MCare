package vn.mcare.system.common.helper.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import vn.mcare.system.common.exception.AuthenticationException;

@Slf4j
@Component
public class JwtProvider implements IJwtProvider {

  @Override
  public String genJwt(String payload) {
    try {
      String issuer = "issuer";
      String secret = "mcareteam";
      Algorithm algorithm = Algorithm.HMAC256(secret);
      return JWT.create()
          .withIssuer(issuer)
          .withIssuedAt(new Date())
          .withClaim("userId", payload)
          .withClaim("createdAt", new Date().getTime())
          .withAudience(payload)
          .sign(algorithm);

    } catch (Exception e) {
      log.error("genJwt error: " + e.getMessage());
      throw new AuthenticationException.JWTException("Algorithm error");
    }
  }
}
