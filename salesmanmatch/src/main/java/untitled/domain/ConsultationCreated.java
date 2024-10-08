package untitled.domain;

import java.util.Date;

import lombok.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
@NoArgsConstructor
public class ConsultationCreated extends AbstractEvent {

    private Long id;
    private String projectname;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private Date consultationdate;
    private Long memo;

    public ConsultationCreated(Object aggregate) {
        super(aggregate);
    }
}