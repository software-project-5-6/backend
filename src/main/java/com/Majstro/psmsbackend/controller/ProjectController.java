package com.Majstro.psmsbackend.controller;

import com.Majstro.psmsbackend.models.Dtos.ProjectDto;
import com.Majstro.psmsbackend.service.ProjectService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    ProjectService _projectService;

    public ProjectController(ProjectService _projectService) {
        this._projectService = _projectService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ProjectDto>> getAllProjects(){

        var result = _projectService.getAllProjects();
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/")
    public ResponseEntity<ProjectDto> createProject(@RequestBody ProjectDto request){

         var result =_projectService.createProjectWithAssignments(request);
         return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProject(@PathVariable UUID id){

        var result = _projectService.deleteProject(id);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<Boolean> updateProject(@RequestBody ProjectDto updateRequest){

        var result = _projectService.updateProject(updateRequest);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }


}
