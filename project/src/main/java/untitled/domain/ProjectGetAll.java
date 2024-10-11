package untitled.domain;

import java.util.Date;
import lombok.*;
import untitled.infra.AbstractEvent;
import com.fasterxml.jackson.annotation.JsonIgnore;


//<<< DDD / Domain Event
@Data
@ToString
public class ProjectGetAll extends AbstractEvent {

    private Long id;
    private String projectname;
    private String year;
    private Double scale;
    private Long target;
    private String host;
    private String summary;
    private String link;
    private Date startDate;
    private Date endDate;

    public ProjectGetAll(Project aggregate) {
        super(aggregate);
    }

    public ProjectGetAll() {
        super();
    }
}

//>>> DDD / Domain Event
