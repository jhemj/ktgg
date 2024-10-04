package untitled.domain;

import java.util.*;
import lombok.*;
import untitled.domain.*;
import untitled.infra.AbstractEvent;

@Data
@ToString
public class ProjectEdited extends AbstractEvent {

    private Long id;
    private String projectname;
    private String year;
    private Double scale;
    private Long target;
    private String host;
    private Long summary;
    private Long link;
    private Date startDate;
    private Date endDate;
    private Date expStartDate;
}