package untitled.domain;

import java.util.Date;
import lombok.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
@NoArgsConstructor
public class ConsultationUpdated extends AbstractEvent {

    private Long id;
    private String projectname;
    private Date consultationdate;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private String memo;
    private String step;

    public ConsultationUpdated(Consultation aggregate) {
        super(aggregate);
        this.id = aggregate.getId();
        this.projectname = aggregate.getProjectname();
        this.consultationdate = aggregate.getConsultationdate();
        this.phone = aggregate.getPhone();
        this.customername = aggregate.getCustomername();
        this.matchedsalesman = aggregate.getMatchedsalesman();
        this.memo = aggregate.getMemo();
        this.step = aggregate.getStep();
    }
}