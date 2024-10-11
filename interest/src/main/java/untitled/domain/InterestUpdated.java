package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class InterestUpdated extends AbstractEvent {

    private Long id;
    private String projectname;
    private Date date;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private Boolean interest;

    public InterestUpdated(Interest aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.projectname = aggregate.getProjectname();
        this.date = aggregate.getDate();
        this.phone = aggregate.getPhone();
        this.customername = aggregate.getCustomername();
        this.matchedsalesman = aggregate.getMatchedsalesman();
        this.interest = aggregate.getInterest();
    }

    public InterestUpdated() {
        super();
    }
}
//>>> DDD / Domain Event
