package untitled.infra;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.ConsultationCreated;
import untitled.domain.InterestCreated;
import untitled.domain.SalesmanMatch;

@Service
@Transactional
public class PolicyHandler {

    @KafkaListener(topics = "untitled", groupId = "salesmanmatch")
    public void handleEvent(ConsumerRecord<String, Object> record) {
        Object payload = record.value();
        System.out.println("##### Received payload type: " + payload.getClass().getName());

        if (payload instanceof ConsultationCreated) {
            ConsultationCreated consultationCreated = (ConsultationCreated) payload;
            System.out.println("##### listener handleConsultationCreated : " + consultationCreated);
            SalesmanMatch.matchSalesman(consultationCreated);
        } else if (payload instanceof InterestCreated) {
            InterestCreated interestCreated = (InterestCreated) payload;
            System.out.println("##### listener handleInterestCreated : " + interestCreated);
            SalesmanMatch.matchSalesman(interestCreated);
        } else {
            System.out.println("##### Unknown event type: " + record.headers().lastHeader("type"));
        }
    }
}