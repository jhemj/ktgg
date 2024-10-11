package untitled.infra;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
            // 이벤트가 ConsultationCreated인 경우
            ConsultationCreated consultationCreated = (ConsultationCreated) payload;
            System.out.println("##### listener handleConsultationCreated : " + consultationCreated);

            // SalesmanMatch 클래스의 매칭 로직 호출
            SalesmanMatch.matchSalesman(consultationCreated);

        } else if (payload instanceof InterestCreated) {
            // 이벤트가 InterestCreated인 경우
            InterestCreated interestCreated = (InterestCreated) payload;
            System.out.println("##### listener handleInterestCreated : " + interestCreated);

            // SalesmanMatch 클래스의 매칭 로직 호출
            SalesmanMatch.matchSalesman(interestCreated);

        } else {
            // 알 수 없는 이벤트 타입 처리
            System.out.println("##### Unknown event type: " + record.headers().lastHeader("type"));
        }
    }
}