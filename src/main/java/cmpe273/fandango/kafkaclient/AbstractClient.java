package cmpe273.fandango.kafkaclient;


import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpStatusCodeException;

import static cmpe273.fandango.constant.KafkaConstant.*;
import static cmpe273.fandango.constant.UrlConstant.HOST;

public abstract class AbstractClient {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${server.port}")
  private String port;

  @Value("${server.contextPath}")
  private String contextPath;

  public abstract void listen(String message);

  protected void handleRequestMessage(String message){

    JSONObject reqObj = new JSONObject(message);

    String apiPath = HOST + ":" + port + contextPath + reqObj.get(API_URL).toString();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);

    if (reqObj.get(METHOD).equals(GET)) handleGet(reqObj, apiPath, headers);
    else if (reqObj.get(METHOD).equals(POST)) handlePost(reqObj, apiPath, headers);
    else if (reqObj.get(METHOD).equals(PUT)) handlePut(reqObj, apiPath, headers);
    else if (reqObj.get(METHOD).equals(DELETE)) handleDelete(reqObj,apiPath,headers);
  }


  private void handleGet (JSONObject reqObj, String apiPath, HttpHeaders headers) {
    try{
      ResponseEntity<String> response = restTemplate.getForEntity(apiPath, String.class);
      handleDataResponse(response, reqObj);
    }catch (HttpStatusCodeException e) {
      handleHttpException(e, headers, reqObj);
    }
  }

  private void handlePost (JSONObject reqObj, String apiPath, HttpHeaders headers) {
    HttpEntity<String> request = new HttpEntity<>(reqObj.get(PARAMS).toString(), headers);
    try {
      ResponseEntity<String> response = restTemplate.exchange(apiPath, HttpMethod.POST, request,String.class);
      // System.out.println(response.getBody());
      handleDataResponse(response, reqObj);
    } catch (HttpStatusCodeException e) {
      handleHttpException(e, headers, reqObj);
    }
  }

  private void handlePut(JSONObject reqObj, String apiPath, HttpHeaders headers) {
    HttpEntity<String> request = new HttpEntity<>(reqObj.get(PARAMS).toString(), headers);
    try{
      ResponseEntity<String> response = restTemplate.exchange(apiPath, HttpMethod.PUT, request, String.class);
      handleDataResponse(response, reqObj);
    } catch (HttpStatusCodeException e) {
      handleHttpException(e, headers, reqObj);
    }
  }

  private void handleDelete(JSONObject reqObj, String apiPath, HttpHeaders headers) {
    try{
      ResponseEntity<String> response = restTemplate.exchange(apiPath, HttpMethod.DELETE,null, String.class);
      handleDataResponse(response, reqObj);
    } catch (HttpStatusCodeException e) {
      handleHttpException(e, headers, reqObj);
    }
  }

  private void handleDataResponse(ResponseEntity<String> response,JSONObject reqObj) {
    JSONObject payload = new JSONObject();
    payload.put(DATA, response.getBody());
    payload.put(REQ_ID, reqObj.get(REQ_ID).toString());
    kafkaTemplate.send(reqObj.get(TOPIC_RES).toString(), payload.toString());
  }

  private void handleHttpException(HttpStatusCodeException e, HttpHeaders headers, JSONObject reqObj) {
    ResponseEntity<String> response = new ResponseEntity<>(e.getResponseBodyAsString(),headers, e.getStatusCode());
    handleErrResponse(response, reqObj);
  }

  private void handleErrResponse(ResponseEntity<String> response,JSONObject reqObj) {
    JSONObject payload = new JSONObject();
    payload.put(ERROR, response.getBody());
    payload.put(REQ_ID, reqObj.get(REQ_ID).toString());
    kafkaTemplate.send(reqObj.get(TOPIC_RES).toString(), payload.toString());
  }

}
