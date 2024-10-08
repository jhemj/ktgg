package untitled.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.ConsultationCreated;
import untitled.domain.InterestCreated;
import untitled.domain.SalesmanMatch;

@Service
@Transactional
public class PolicyHandler {

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InterestCreated'"
    )
    public void wheneverInterestCreated(@Payload InterestCreated interestCreated) {
        System.out.println("\n\n##### listener MatchSalesman : " + interestCreated + "\n\n");
        SalesmanMatch.matchSalesman(interestCreated);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultationCreated'"
    )
    public void wheneverConsultationCreated(@Payload ConsultationCreated consultationCreated) {
        System.out.println("\n\n##### listener MatchSalesman : " + consultationCreated + "\n\n");
        SalesmanMatch.matchSalesman(consultationCreated);
    }
}