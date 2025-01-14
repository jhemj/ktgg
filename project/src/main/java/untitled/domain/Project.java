package untitled.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;
import javax.transaction.Transactional;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import untitled.ProjectApplication;

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

    private String summary;

    private String link;

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

    public static Project createProject(Project project) {
        repository().save(project);
        return project;
    }

    public ProjectStarted startProject(Long id, ProjectStarted request) {

        Project project = repository().findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        project.setStartDate(request.getStartDate());

        ProjectStarted projectStarted = new ProjectStarted(project);
        projectStarted.publishAfterCommit();

        return projectStarted;
    }

    public ProjectEnded endProject(Long id, ProjectEnded request) {
        
        Project project = repository().findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        project.setEndDate(request.getEndDate());

        ProjectEnded projectEnded = new ProjectEnded(project);
        projectEnded.publishAfterCommit();

        return projectEnded;

    }

    public ProjectEdited editProject(Long id, ProjectEdited request) {

        Project project = repository().findById(id).orElseThrow(() -> new EntityNotFoundException("Project not found with id: " + id));

        project.setProjectname(request.getProjectname());
        project.setYear(request.getYear());
        project.setScale(request.getScale());
        project.setTarget(request.getTarget());
        project.setHost(request.getHost());
        project.setSummary(request.getSummary());
        project.setLink(request.getLink());
        project.setStartDate(request.getStartDate());
        project.setEndDate(request.getEndDate());
        project.setExpStartDate(request.getExpStartDate());

        ProjectEdited projectEdited = new ProjectEdited(project);
        projectEdited.publishAfterCommit();

        return projectEdited;
    }

    
    @Transactional
    public List<ProjectGetAll> getAllProject() {
        Iterable<Project> iterableProjects = repository().findAll();
        List<ProjectGetAll> projects = new ArrayList<>();
        for (Project project : iterableProjects) {
            ProjectGetAll item = new ProjectGetAll(project);
            projects.add(item);
        }
    
        return projects; // List<Project>를 반환
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
