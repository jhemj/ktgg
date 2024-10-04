package untitled.infra;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import untitled.config.kafka.KafkaProcessor;
import untitled.domain.*;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    SalesmanMatchRepository salesmanMatchRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InterestCreated'"
    )
    public void wheneverInterestCreated_MatchSalesman(
        @Payload InterestCreated interestCreated
    ) {
        InterestCreated event = interestCreated;
        System.out.println(
            "\n\n##### listener MatchSalesman : " + interestCreated + "\n\n"
        );

        // Sample Logic //
        SalesmanMatch.matchSalesman(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultationCreated'"
    )
    public void wheneverConsultationCreated_MatchSalesman(
        @Payload ConsultationCreated consultationCreated
    ) {
        ConsultationCreated event = consultationCreated;
        System.out.println(
            "\n\n##### listener MatchSalesman : " + consultationCreated + "\n\n"
        );

        // Sample Logic //
        SalesmanMatch.matchSalesman(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
