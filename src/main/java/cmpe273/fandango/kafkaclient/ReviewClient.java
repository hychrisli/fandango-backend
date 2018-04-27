package cmpe273.fandango.kafkaclient;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static cmpe273.fandango.constant.KafkaConstant.KAFKA_TOPIC_REVIEWS;

@Component
public class ReviewClient extends AbstractClient {

  @Override
  @KafkaListener(topics = KAFKA_TOPIC_REVIEWS)
  public void listen(String message) {
    handleRequestMessage(message);
  }

}
