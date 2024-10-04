package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProjectCreated extends AbstractEvent {

    private Long id;
    private Long name;
    private String year;
    private Double scale;
    private Long target;
    private Long host;
    private Long summary;
    private Long link;
    private Date startDate;
    private Date endDate;
    private Date expStartDate;
    private String projectname;
    private String host;
    private Long summary;

    public ProjectCreated(Project aggregate) {
        super(aggregate);
    }

    public ProjectCreated() {
        super();
    }
}
//>>> DDD / Domain Event
