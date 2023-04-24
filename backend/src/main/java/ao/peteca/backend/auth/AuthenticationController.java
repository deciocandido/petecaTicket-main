package ao.peteca.backend.auth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponse> register(
      @RequestBody RegisterRequest request
  ) {
    logger.info("[getWarnning] START REGISTATION - INFO");
    logger.warn("[getWarnning] START REGISTATION - WARN");
    logger.error("[getWarnning] START REGISTATION - ERROR");
    logger.debug("[getWarnning] START REGISTATION - DEBUG");
    logger.trace("[getWarnning] START REGISTATION - TRACE");
    System.out.println("----- START REGISTATION ----- ");
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponse> authenticate(
      @RequestBody AuthenticationRequest request
  ) {
    logger.info("[getWarnning] START AUTHENTICATION - INFO");
    logger.warn("[getWarnning] START AUTHENTICATION - WARN");
    logger.error("[getWarnning] START AUTHENTICATION - ERROR");
    logger.debug("[getWarnning] START AUTHENTICATION - DEBUG");
    logger.trace("[getWarnning] START AUTHENTICATION - TRACE");
    System.out.println("----- START AUTHENTICATION ----- ");
    return ResponseEntity.ok(service.authenticate(request));
  }


}