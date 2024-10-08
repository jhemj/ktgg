package untitled.domain;

import java.util.Date;

import lombok.*;
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

    public InterestCreated(Object aggregate) {
        super(aggregate);
    }
}