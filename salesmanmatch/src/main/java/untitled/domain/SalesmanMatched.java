package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class SalesmanMatched extends AbstractEvent {

    private Long id;

    public SalesmanMatched(SalesmanMatch aggregate) {
        super(aggregate);
    }

    public SalesmanMatched() {
        super();
    }
}
//>>> DDD / Domain Event
