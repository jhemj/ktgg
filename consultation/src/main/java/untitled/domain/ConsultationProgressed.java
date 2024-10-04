package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ConsultationProgressed extends AbstractEvent {

    private Long id;

    public ConsultationProgressed(Consultation aggregate) {
        super(aggregate);
    }

    public ConsultationProgressed() {
        super();
    }
}
//>>> DDD / Domain Event
