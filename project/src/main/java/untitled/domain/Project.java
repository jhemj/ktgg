package untitled.domain;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;
import untitled.ProjectApplication;
import untitled.domain.ProjectCreated;
import untitled.domain.ProjectEdited;
import untitled.domain.ProjectEnded;
import untitled.domain.ProjectStarted;

@Entity
@Table(name = "Project_table")
@Data
//<<< DDD / Aggregate Root
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    @PostPersist
    public void onPostPersist() {
        ProjectCreated projectCreated = new ProjectCreated(this);
        projectCreated.publishAfterCommit();
    }

    @PostUpdate
    public void onPostUpdate() {
        ProjectStarted projectStarted = new ProjectStarted(this);
        projectStarted.publishAfterCommit();

        ProjectEnded projectEnded = new ProjectEnded(this);
        projectEnded.publishAfterCommit();

        ProjectEdited projectEdited = new ProjectEdited(this);
        projectEdited.publishAfterCommit();
    }

    @PreUpdate
    public void onPreUpdate() {}

    public static ProjectRepository repository() {
        ProjectRepository projectRepository = ProjectApplication.applicationContext.getBean(
            ProjectRepository.class
        );
        return projectRepository;
    }

    public void createProject() {
        //implement business logic here:

        ProjectCreated projectCreated = new ProjectCreated(this);
        projectCreated.publishAfterCommit();
    }

    public void startProject() {
        //implement business logic here:

        ProjectStarted projectStarted = new ProjectStarted(this);
        projectStarted.publishAfterCommit();
    }

    public void endProject() {
        //implement business logic here:

        ProjectEnded projectEnded = new ProjectEnded(this);
        projectEnded.publishAfterCommit();
    }

    public void editProject() {
        //implement business logic here:

        ProjectEdited projectEdited = new ProjectEdited(this);
        projectEdited.publishAfterCommit();
    }

    //<<< Clean Arch / Port Method
    public static void annualProjectCreate(ProjectEnded projectEnded) {
        //implement business logic here:

        /** Example 1:  new item 
        Project project = new Project();
        repository().save(project);

        */

        /** Example 2:  finding and process
        
        repository().findById(projectEnded.get???()).ifPresent(project->{
            
            project // do something
            repository().save(project);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
