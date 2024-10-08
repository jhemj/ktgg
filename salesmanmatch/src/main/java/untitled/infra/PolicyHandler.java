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

    private final ObjectMapper objectMapper = new ObjectMapper();

    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {
        try {
            // 공통 이벤트 클래스로 역직렬화
            AbstractEvent event = objectMapper.readValue(message, AbstractEvent.class);

            if ("InterestCreated".equals(event.getEventType())) {
                InterestCreated interestCreated = objectMapper.readValue(message, InterestCreated.class);
                System.out.println("\n\n##### listener MatchSalesman : " + interestCreated + "\n\n");
                SalesmanMatch.matchSalesman(interestCreated);
            } else if ("ConsultationCreated".equals(event.getEventType())) {
                ConsultationCreated consultationCreated = objectMapper.readValue(message, ConsultationCreated.class);
                System.out.println("\n\n##### listener MatchSalesman : " + consultationCreated + "\n\n");
                SalesmanMatch.matchSalesman(consultationCreated);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}