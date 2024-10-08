package untitled.infra;

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

    // @PostMapping("")
    // ResponseEntity<ProjectCreated> createProject(@Valid @RequestBody ProjectCreated request) {
    //     ProjectCreated response = projectService.createProject(request);
    //     System.out.println("pased");
    //     return ResponseEntity.status(HttpStatus.CREATED).body(response);
    // }

    @PostMapping("")
    public ResponseEntity<Project> createProject(@Valid @RequestBody ProjectCreated request) {
        Project project = new Project();
        project.createProject(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(project);
    }

    @GetMapping("")
    ResponseEntity<ProjectGetAll> getAllProject() {
        ProjectGetAll response = new Project().getAllProject();
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

}
