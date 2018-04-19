package cmpe273.fandango.controller;


import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.response.JsonResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static cmpe273.fandango.constant.JsonConstant.KEY_ERROR;

public abstract class AbstractController extends JsonResponseHandler {

    protected <T> ResponseEntity<JsonResponse> success(String key, T data) {
	    return genericResponse(new JsonResponse(key, data), HttpStatus.OK);
    }

    protected ResponseEntity<JsonResponse> notFound() {
	    return genericResponse(new JsonResponse(KEY_ERROR, HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }

    protected <T> ResponseEntity<JsonResponse> created(String key, T data) {
	    return genericResponse(new JsonResponse(key, data), HttpStatus.CREATED);
    }

    protected ResponseEntity<JsonResponse> conflict() {
	    return genericResponse(new JsonResponse(KEY_ERROR, HttpStatus.CONFLICT.name()), HttpStatus.CONFLICT);
    }
    
    protected ResponseEntity<JsonResponse> runOutOfDishes(String dishName, short remain) {
    	return genericResponse(new JsonResponse(KEY_ERROR, "No enough dishes! " + dishName + "only remains " + remain), HttpStatus.CONFLICT);
    }
    
    protected ResponseEntity<JsonResponse> noValidCoupon() {
    	return genericResponse(new JsonResponse(KEY_ERROR, "No valid Coupon" + HttpStatus.NOT_FOUND.name()), HttpStatus.NOT_FOUND);
    }
}
