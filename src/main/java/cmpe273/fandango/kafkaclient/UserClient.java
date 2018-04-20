package cmpe273.fandango.kafkaclient;

import cmpe273.fandango.response.JsonResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static cmpe273.fandango.constant.KafkaConstant.*;
import static cmpe273.fandango.constant.UrlConstant.HOST;


@Component
public class UserClient {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private RestTemplate restTemplate = new RestTemplate();

  @Value("${server.port}")
  private String port;

  @Value("${server.contextPath}")
  private String contextPath;

  @KafkaListener(topics = KAFKA_TOPIC_USERS)
  public void listen(String message) {

    String apiPath = HOST + ":" + port + contextPath;
    System.out.println("Received Message: " + message);
    JSONObject reqObj = new JSONObject(message);
    System.out.println(reqObj.get(METHOD).toString() + " " + reqObj.get(API_URL).toString());
    if (reqObj.get(METHOD).equals(GET)){
      ResponseEntity<String> response = restTemplate.getForEntity(apiPath + reqObj.get(API_URL).toString(), String.class);
        System.out.print(response.getBody());
      JSONObject payload = new JSONObject();
      payload.put(DATA, response.getBody());
      payload.put(REQ_ID, reqObj.get(REQ_ID));
      kafkaTemplate.send(reqObj.get(TOPIC_RES).toString(), payload.toString());
    }




   /* if (message.equals("Hello"))
      sendMessage("I got this message");*/
  }

/*
  private void sendMessage(String msg) {
    kafkaTemplate.send(FAN_USER, msg);
  }
*/

}
