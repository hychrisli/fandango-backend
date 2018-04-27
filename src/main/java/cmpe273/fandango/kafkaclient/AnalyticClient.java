package cmpe273.fandango.kafkaclient;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static cmpe273.fandango.constant.KafkaConstant.KAFKA_TOPIC_ANALYTICS;

@Component
public class AnalyticClient extends AbstractClient {

  @Override
  @KafkaListener(topics = KAFKA_TOPIC_ANALYTICS)
  public void listen(String message) {
    handleRequestMessage(message);
  }

}
