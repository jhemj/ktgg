package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SalesmanAlarmed extends AbstractEvent {

    private Long id;
    private String eventType;
    private String eventData;
    private String salesmanId;
    private Timestamp timestamp;

    public SalesmanAlarmed(Alarm aggregate) {
        super(aggregate);
    }

    public SalesmanAlarmed() {
        super();
    }
}
//>>> DDD / Domain Event
