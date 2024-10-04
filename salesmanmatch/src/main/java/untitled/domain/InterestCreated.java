package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class InterestCreated extends AbstractEvent {

    private Long id;
    private String projectname;
    private Date date;
    private String phone;
    private String customername;
    private String matchedsalesman;
    private Boolean interest;
}
