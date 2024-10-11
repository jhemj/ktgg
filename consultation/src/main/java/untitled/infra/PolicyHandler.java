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

    @StreamListener(KafkaProcessor.INPUT)
public void wheneverSalesmanMatched(@Payload SalesmanMatched salesmanMatched) {
    // 본인의 특정 Consultation 객체에 해당하는 이벤트만 처리
    Long myConsultationId = 12345L;  // 여기서는 본인의 Consultation 객체 ID를 사용
    if (salesmanMatched.getConsultationId() != null && salesmanMatched.getConsultationId().equals(myConsultationId)) {
        System.out.println("##### listener editSalesman : " + salesmanMatched);
        Consultation.editSalesman(salesmanMatched);
    } else {
        System.out.println("##### Skipping unmatched event for Consultation ID: " + salesmanMatched.getConsultationId());
    }
}
}