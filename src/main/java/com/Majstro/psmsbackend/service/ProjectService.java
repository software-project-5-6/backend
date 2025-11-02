package com.Majstro.psmsbackend.service;

import com.Majstro.psmsbackend.exception.ProjectNotFoundException;
import com.Majstro.psmsbackend.exception.UserNotFoundException;
import com.Majstro.psmsbackend.mapper.ProjectMapper;
import com.Majstro.psmsbackend.models.EntityModels.Project;
import com.Majstro.psmsbackend.models.EntityModels.ProjectAssignment;
import com.Majstro.psmsbackend.models.Dtos.ProjectDto;
import com.Majstro.psmsbackend.models.Dtos.UserWithRole;
import com.Majstro.psmsbackend.repo.ProjectAssignmentRepository;
import com.Majstro.psmsbackend.repo.ProjectRepository;
import com.Majstro.psmsbackend.repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    ProjectRepository _projectRepository;
    ProjectAssignmentRepository _projectAssignmentRepository;
    UserRepository _userRepository;


    public ProjectService(ProjectRepository _projectRepository,
                          ProjectAssignmentRepository _projectAssignmentRepository,
                          UserRepository _userRepository
                          ) {
        this._projectRepository = _projectRepository;
        this._projectAssignmentRepository = _projectAssignmentRepository;
        this._userRepository = _userRepository;

    }



    public List<ProjectDto> getAllProjects(){
        var projectList = _projectRepository.findAll();

        List<ProjectDto> listToSend =projectList.stream()
                .map(x->ProjectMapper.ProjectToProjectDto(x))
                .toList();

          return listToSend;

    }



    public ProjectDto createProjectWithAssignments(ProjectDto request){


        Project newProject = new Project();
        ProjectMapper.ProjectDtoToProjectPartiallyExtractor(request,newProject);

        var createdproject = createProject(newProject);
        var createdAssignments = createProjectAssignments(createdproject, request.getUserRoleList());

        request.setProjectId(createdproject.getId());
        request.setUserRoleList(createdAssignments);

        return request;
    }



    private Project createProject(Project newProject){
        return _projectRepository.save(newProject);

    }



    private List<UserWithRole> createProjectAssignments(
            Project project, List<UserWithRole> userRoleList){

       for(UserWithRole x : userRoleList){

          ProjectAssignment assignment = new ProjectAssignment();
          assignment.setProject(project);

          var user = _userRepository.findById(x.getUserId())
                  .orElseThrow(()-> new UserNotFoundException("User not found with ID: " + x.getUserId()));

              assignment.setUser(user);
              assignment.setRole(x.getRole());

           var result = _projectAssignmentRepository.save(assignment);
           x.setAssignmentId(result.getId());
       }
        return userRoleList;
    }



    @Transactional
    public boolean deleteProject(UUID project_id){
        var projectFound = _projectRepository.findById(project_id)
                .orElseThrow(()-> new ProjectNotFoundException("Project with id "+project_id+" not found"));

        _projectRepository.delete(projectFound);
        return true;

    }



    public Project getProjectById(UUID project_id){

        var projectExist = _projectRepository.findById(project_id)
                .orElseThrow(()->new ProjectNotFoundException("Project with id "+project_id+" not found"));
            return projectExist;
    }



    @Transactional
    public Boolean updateProject(UUID projectId, ProjectDto updateRequest){

        var existingProject = _projectRepository.findById(projectId)
                .orElseThrow(()->new ProjectNotFoundException("Project with id " + updateRequest.getProjectId() + " not found"));

            var updatingProject = existingProject;

            ProjectMapper.ProjectDtoToProjectPartiallyExtractor(updateRequest, updatingProject);

            updatingProject.getAssignments().clear();

            for(UserWithRole x : updateRequest.getUserRoleList()){
                ProjectAssignment newAssignment = new ProjectAssignment();
                newAssignment.setProject(updatingProject);

                var user = _userRepository.findById(x.getUserId())
                        .orElseThrow(()->new UserNotFoundException("User Not Found with Id " + x.getUserId()));

                newAssignment.setUser(user);
                newAssignment.setRole(x.getRole());

                updatingProject.getAssignments().add(newAssignment);
            }
            _projectRepository.save(updatingProject);
            return true;

    }

}
