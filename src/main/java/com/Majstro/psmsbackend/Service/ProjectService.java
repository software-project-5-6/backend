package com.Majstro.psmsbackend.Service;

import com.Majstro.psmsbackend.Mapper.ProjectMapper;
import com.Majstro.psmsbackend.Models.EntityModels.Project;
import com.Majstro.psmsbackend.Models.EntityModels.ProjectAssignment;
import com.Majstro.psmsbackend.Models.Dtos.ProjectDto;
import com.Majstro.psmsbackend.Models.Dtos.UserWithRole;
import com.Majstro.psmsbackend.Repo.ProjectAssignmentRepository;
import com.Majstro.psmsbackend.Repo.ProjectRepository;
import com.Majstro.psmsbackend.Repo.RoleRepository;
import com.Majstro.psmsbackend.Repo.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {

    ProjectRepository _projectRepository;
    ProjectAssignmentRepository _projectAssignmentRepository;
    UserRepository _userRepository;
    RoleRepository _roleRepository;

    public ProjectService(ProjectRepository _projectRepository,
                          ProjectAssignmentRepository _projectAssignmentRepository,
                          UserRepository _userRepository,
                          RoleRepository _roleRepository) {
        this._projectRepository = _projectRepository;
        this._projectAssignmentRepository = _projectAssignmentRepository;
        this._userRepository = _userRepository;
        this._roleRepository = _roleRepository;
    }



    public List<ProjectDto> getAllProjects(){
        var projectList = _projectRepository.findAll();

        List<ProjectDto> listToSend = new ArrayList<>();
        for(Project x : projectList){

            var newProjectDto = ProjectMapper.ProjectToProjectDto(x);
            listToSend.add(newProjectDto);
        }
          return listToSend;

    }



    public ProjectDto createProjectWithAssignments(ProjectDto request){


        Project newProject = new Project();
        ProjectMapper.ProjectDtoToProjectPartiallyExtractor(request,newProject);

        var createdproject = createProject(newProject);
        var createdAssignments =createProjectAssignments(createdproject,request.userRoleList);

        request.projectId=createdproject.getId();
        request.userRoleList=createdAssignments;

        return request;
    }



    private Project createProject(Project newProject){
        return _projectRepository.save(newProject);

    }

    private List<UserWithRole> createProjectAssignments(
            Project project,List<UserWithRole> userRoleList){

       for(UserWithRole x : userRoleList){

          ProjectAssignment assignment = new ProjectAssignment();
          assignment.setProject(project);

          var user = _userRepository.findById(x.userId);
          if(user.isPresent()){
              assignment.setUser(user.get());
          }

          var role =_roleRepository.findByRoleName(x.role);
           if(role.isPresent()){
               assignment.setRole(role.get());
           }

           var result = _projectAssignmentRepository.save(assignment);
           x.setAssignmentId(result.getId());
       }

        return userRoleList;
    }


    @Transactional
    public boolean deleteProject(UUID project_id){
        var projectFound = _projectRepository.findById(project_id);

        if(projectFound.isPresent()){
            _projectRepository.delete(projectFound.get());
            return true;
        }

        return false;
    }



    public Project getProjectById(UUID project_id){

        var projectExist = _projectRepository.findById(project_id);
        if(projectExist.isPresent()){
            return projectExist.get();
        }
        return (Project) null;
    }


    @Transactional
    public Boolean updateProject(ProjectDto updateRequest){

        var existingProject = _projectRepository.findById(updateRequest.projectId);

        if(existingProject.isPresent()){
            var updatingProject =existingProject.get();

            ProjectMapper.ProjectDtoToProjectPartiallyExtractor(updateRequest,updatingProject);

            updatingProject.getAssignments().clear();

            for(UserWithRole x : updateRequest.userRoleList){
                ProjectAssignment newAssignment = new ProjectAssignment();

                newAssignment.setProject(updatingProject);

                var user = _userRepository.findById(x.userId);
                var role = _roleRepository.findByRoleName(x.role);

                user.ifPresent(newAssignment::setUser);
                role.ifPresent(newAssignment::setRole);

                updatingProject.getAssignments().add(newAssignment);
            }
            _projectRepository.save(updatingProject);
            return true;
        }
        return false;

    }



}
