package ao.peteca.backend.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {

  Logger logger = LoggerFactory.getLogger(DemoController.class);

  @GetMapping
  public ResponseEntity<String> sayHello() {
    logger.info("[getWarnning] START REGISTATION - INFO");
    logger.warn("[getWarnning] START REGISTATION - WARN");
    logger.error("[getWarnning] START REGISTATION - ERROR");
    logger.debug("[getWarnning] START REGISTATION - DEBUG");
    logger.trace("[getWarnning] START REGISTATION - TRACE");
    return ResponseEntity.ok("Hello from secured endpoint");
  }

}