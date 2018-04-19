package cmpe273.fandango.kafkaclient;

import static cmpe273.fandango.constant.TopicConstant.FAN_USER;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
public class UserClient {

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @KafkaListener(topics = FAN_USER, group = "foo")
  public void listen(String message) {
    System.out.println("Received Message in group foo: " + message);
    if (message.equals("Hello"))
      sendMessage("I got this message");
  }

  private void sendMessage(String msg) {
    kafkaTemplate.send(FAN_USER, msg);
  }

}
