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

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='SalesmanMatched'"
    )
    public void wheneverSalesmanMatched(@Payload SalesmanMatched salesmanMatched) {
        System.out.println("##### listener editSalesman : " + salesmanMatched);
        Consultation.editSalesman(salesmanMatched);
    }
}