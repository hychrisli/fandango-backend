package cmpe273.fandango.exception;

import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.response.JsonResponseHandler;
import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static cmpe273.fandango.exception.ErrorCode.ERR_INTEGRITY_VIOLATION;
import static cmpe273.fandango.exception.ErrorCode.ERR_INTERNAL_SERVER_ERROR;
import static cmpe273.fandango.exception.ErrorCode.ERR_BAD_REQUEST;
import static org.slf4j.LoggerFactory.getLogger;

@RestControllerAdvice
public class ControllerExceptionHandler extends JsonResponseHandler {

  private final static Logger LOGGER = getLogger(ControllerExceptionHandler.class);

  @ExceptionHandler(AppException.class)
  public ResponseEntity<JsonResponse> handleAppException(AppException appException) {
    LOGGER.error(appException.getErrorCode().name(), appException);
    return failure(appException.getErrorCode(), appException.getMessage());
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<JsonResponse> handleBadRequestException(HttpMessageNotReadableException e) {
    LOGGER.error(ERR_BAD_REQUEST.name(), e);
    return badRequest(e.getMessage());
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<JsonResponse> handleIntegrityViolationException(DataIntegrityViolationException e) {
    LOGGER.error(ERR_INTEGRITY_VIOLATION.name(), e);
    return failure(ERR_INTEGRITY_VIOLATION, e.getRootCause().getMessage());
  }


  @ExceptionHandler(Exception.class)
  public ResponseEntity<JsonResponse> handleException(Exception e) {
    LOGGER.error(ERR_INTERNAL_SERVER_ERROR.name(), e);
    return failure(ERR_INTERNAL_SERVER_ERROR, e.getMessage());
  }

}
