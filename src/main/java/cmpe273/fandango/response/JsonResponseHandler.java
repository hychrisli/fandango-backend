package cmpe273.fandango.response;

import cmpe273.fandango.exception.ErrorCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static cmpe273.fandango.constant.JsonConstant.KEY_ERROR;
import static cmpe273.fandango.constant.JsonConstant.KEY_MESSAGE;
import static cmpe273.fandango.exception.ErrorCode.ERR_BAD_REQUEST;

public abstract class JsonResponseHandler {

    protected ResponseEntity<JsonResponse> genericResponse(JsonResponse jsonResponse, HttpStatus httpStatus) {
	    return new ResponseEntity<JsonResponse>(jsonResponse, httpStatus);
    }

    protected ResponseEntity<JsonResponse> responseWithCustHeaders(JsonResponse jsonResponse, HttpHeaders headers,
	    HttpStatus httpStatus) {
	    return new ResponseEntity<JsonResponse>(jsonResponse, headers, httpStatus);
    }

    protected ResponseEntity<JsonResponse> failure(ErrorCode errorCode, String msg) {
	    JsonResponse jsonResponse = new JsonResponse(KEY_ERROR, errorCode.name()).addPair(KEY_MESSAGE, msg);
	    return genericResponse(jsonResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    protected ResponseEntity<JsonResponse> badRequest(String msg) {
	    JsonResponse jsonResponse = new JsonResponse(KEY_ERROR, ERR_BAD_REQUEST).addPair(KEY_MESSAGE, msg);
	    return genericResponse(jsonResponse, HttpStatus.BAD_REQUEST);
    }
}