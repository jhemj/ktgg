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
    ProjectRepository projectRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectEnded'"
    )
    public void wheneverProjectEnded_AnnualProjectCreate(
        @Payload ProjectEnded projectEnded
    ) {
        ProjectEnded event = projectEnded;
        System.out.println(
            "\n\n##### listener AnnualProjectCreate : " + projectEnded + "\n\n"
        );

        // Sample Logic //
        Project.annualProjectCreate(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
