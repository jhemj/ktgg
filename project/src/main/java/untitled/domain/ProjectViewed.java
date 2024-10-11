package untitled.domain;

import java.time.LocalDate;
import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProjectViewed extends AbstractEvent {

    private Long id;
    private String projectname;
    private String year;
    private Double scale;
    private String target;
    private String host;
    private String summary;
    private String link;
    private Date startDate;
    private Date endDate;
    private Date expStartDate;

    public ProjectViewed(Project aggregate) {
        super(aggregate);
    }

    public ProjectViewed() {
        super();
    }
}
//>>> DDD / Domain Event
