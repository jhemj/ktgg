package untitled.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.Consultation;
import untitled.domain.ConsultationRepository;
import untitled.domain.SalesmanMatched;

@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ConsultationRepository consultationRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @StreamListener(KafkaProcessor.INPUT)
    public void onEvent(@Payload String message) {
        try {
            AbstractEvent event = objectMapper.readValue(message, AbstractEvent.class);

            if ("SalesmanMatched".equals(event.getEventType())) {
                SalesmanMatched salesmanMatched = objectMapper.readValue(message, SalesmanMatched.class);
                System.out.println("\n\n##### listener editSalesman : " + salesmanMatched + "\n\n");
                Consultation.editSalesman(salesmanMatched);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}