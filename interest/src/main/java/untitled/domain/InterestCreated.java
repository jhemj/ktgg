package untitled.domain;

import java.util.Date;
import lombok.*;
import untitled.domain.Interest;
import untitled.infra.AbstractEvent;

@Data
@ToString
@NoArgsConstructor
public class InterestCreated extends AbstractEvent {

    private Long id;
    private String projectname;
    private Date date;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private Boolean interest;

    public InterestCreated(Interest aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.projectname = aggregate.getProjectname();
        this.date = aggregate.getDate();
        this.phone = aggregate.getPhone();
        this.customername = aggregate.getCustomername();
        this.matchedsalesman = aggregate.getMatchedsalesman();
        this.interest = aggregate.getInterest();
    }
}