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
    AlarmRepository alarmRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultationCreated'"
    )
    public void wheneverConsultationCreated_CreateAlarm(
        @Payload ConsultationCreated consultationCreated
    ) {
        ConsultationCreated event = consultationCreated;
        System.out.println(
            "\n\n##### listener CreateAlarm : " + consultationCreated + "\n\n"
        );

        // Sample Logic //
        Alarm.createAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectCreated'"
    )
    public void wheneverProjectCreated_CreateAlarm(
        @Payload ProjectCreated projectCreated
    ) {
        ProjectCreated event = projectCreated;
        System.out.println(
            "\n\n##### listener CreateAlarm : " + projectCreated + "\n\n"
        );

        // Sample Logic //
        Alarm.createAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InterestCreated'"
    )
    public void wheneverInterestCreated_CreateAlarm(
        @Payload InterestCreated interestCreated
    ) {
        InterestCreated event = interestCreated;
        System.out.println(
            "\n\n##### listener CreateAlarm : " + interestCreated + "\n\n"
        );

        // Sample Logic //
        Alarm.createAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectStarted'"
    )
    public void wheneverProjectStarted_EditAlarm(
        @Payload ProjectStarted projectStarted
    ) {
        ProjectStarted event = projectStarted;
        System.out.println(
            "\n\n##### listener EditAlarm : " + projectStarted + "\n\n"
        );

        // Sample Logic //
        Alarm.editAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectEnded'"
    )
    public void wheneverProjectEnded_EditAlarm(
        @Payload ProjectEnded projectEnded
    ) {
        ProjectEnded event = projectEnded;
        System.out.println(
            "\n\n##### listener EditAlarm : " + projectEnded + "\n\n"
        );

        // Sample Logic //
        Alarm.editAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ProjectEdited'"
    )
    public void wheneverProjectEdited_EditAlarm(
        @Payload ProjectEdited projectEdited
    ) {
        ProjectEdited event = projectEdited;
        System.out.println(
            "\n\n##### listener EditAlarm : " + projectEdited + "\n\n"
        );

        // Sample Logic //
        Alarm.editAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='InterestUpdated'"
    )
    public void wheneverInterestUpdated_EditAlarm(
        @Payload InterestUpdated interestUpdated
    ) {
        InterestUpdated event = interestUpdated;
        System.out.println(
            "\n\n##### listener EditAlarm : " + interestUpdated + "\n\n"
        );

        // Sample Logic //
        Alarm.editAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultationEdited'"
    )
    public void wheneverConsultationEdited_EditAlarm(
        @Payload ConsultationEdited consultationEdited
    ) {
        ConsultationEdited event = consultationEdited;
        System.out.println(
            "\n\n##### listener EditAlarm : " + consultationEdited + "\n\n"
        );

        // Sample Logic //
        Alarm.editAlarm(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='ConsultationProgressed'"
    )
    public void wheneverConsultationProgressed_EditAlarm(
        @Payload ConsultationProgressed consultationProgressed
    ) {
        ConsultationProgressed event = consultationProgressed;
        System.out.println(
            "\n\n##### listener EditAlarm : " + consultationProgressed + "\n\n"
        );

        // Sample Logic //
        Alarm.editAlarm(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
