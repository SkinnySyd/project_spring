package com.rabat.testProject.controller;

import com.rabat.testProject.Dto.ProjectDto;
import com.rabat.testProject.Model.Project;
//import com.rabat.testProject.Service.ProjectService;
import com.rabat.testProject.Service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//@CrossOrigin(origins = "http://localhost:4000", maxAge = 3600, allowCredentials="true")
@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectService projectService;


    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProjectDto>> getAllProject(){
        List<ProjectDto> projects = projectService.getAllProjects();
        return ResponseEntity.ok(projects);
    }

    @PostMapping("/create")
    public ResponseEntity<Project> createProject(@RequestBody ProjectDto project){
        projectService.createProject(project);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/update/{id}")
    public ResponseEntity<Project> updateProjectById(@PathVariable long id, @RequestBody Project project){
        projectService.updateProject(id,project);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<Project> updateProject(@RequestBody Project project){
        projectService.updateProject(project);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
