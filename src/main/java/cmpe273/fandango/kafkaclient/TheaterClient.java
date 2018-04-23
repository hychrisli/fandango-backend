package cmpe273.fandango.kafkaclient;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static cmpe273.fandango.constant.KafkaConstant.KAFKA_TOPIC_THEATERS;

@Component
public class TheaterClient extends AbstractClient {
  @Override
  @KafkaListener(topics = KAFKA_TOPIC_THEATERS)
  public void listen(String message) {
    handleRequestMessage(message);
  }

}
