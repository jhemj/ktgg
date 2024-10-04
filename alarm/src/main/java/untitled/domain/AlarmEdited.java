package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class AlarmEdited extends AbstractEvent {

    private Long id;

    public AlarmEdited(Alarm aggregate) {
        super(aggregate);
    }

    public AlarmEdited() {
        super();
    }
}
//>>> DDD / Domain Event
