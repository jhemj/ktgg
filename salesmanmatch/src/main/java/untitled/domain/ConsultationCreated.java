package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ConsultationCreated extends AbstractEvent {

    private Long id;
    private String projectname;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private Date consultationdate;
    private Long memo;
}
