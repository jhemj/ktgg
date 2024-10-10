package untitled.infra;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import untitled.domain.*;

@RestController
@RequestMapping(value = "/projects")
@RequiredArgsConstructor
@Transactional
public class ProjectController {

    @PostMapping("")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project request) {
        Project createdProject = Project.createProject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProject);
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<ProjectGetAll>> getAllProjects() {
        List<ProjectGetAll> response = new Project().getAllProject();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/start")
    ResponseEntity<ProjectStarted> startProject(@Valid @PathVariable Long id, @Valid @RequestBody ProjectStarted request) {
        ProjectStarted response = new Project().startProject(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/end")
    ResponseEntity<ProjectEnded> endProject(@Valid @PathVariable Long id, @Valid @RequestBody ProjectEnded request) {
        ProjectEnded response = new Project().endProject(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{id}/edit")
    ResponseEntity<ProjectEdited> editProject(@Valid @PathVariable Long id, @Valid @RequestBody ProjectEdited request) {
        ProjectEdited response = new Project().editProject(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/ongoing")
    public ResponseEntity<List<ProjectGetAll>> getOngoingProjects() {
        List<ProjectGetAll> response = new Project().getAllProject();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
