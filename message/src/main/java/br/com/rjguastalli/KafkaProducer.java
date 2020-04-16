package br.com.rjguastalli;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${cloudkarafka.topic}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void send(KafkaMessage kafkaMessage) {
        try {
            var message = convertObjetoToString(kafkaMessage);
            this.kafkaTemplate.send(topic, message);
            log.info("Mensagem enviada [" + message + "] para " + topic);
        } catch (JsonProcessingException je) {
            log.error("Ocorreu erro ai enviar a mensagem.");
        }
    }

    private String convertObjetoToString(KafkaMessage kafkaMessage) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(kafkaMessage);
    }


}
