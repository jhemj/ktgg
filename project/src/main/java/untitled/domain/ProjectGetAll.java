package untitled.domain;

import java.util.List;
import lombok.*;
import untitled.infra.AbstractEvent;

//<<< DDD / Domain Event
@Data
@ToString
public class ProjectGetAll extends AbstractEvent {

    private List<Project> projects; 

    public ProjectGetAll(List<Project> aggregate) {
        super(aggregate);
    }

    public ProjectGetAll() {
        super();
    }
}
//>>> DDD / Domain Event
