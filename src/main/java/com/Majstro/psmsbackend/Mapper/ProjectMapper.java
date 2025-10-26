package com.Majstro.psmsbackend.Mapper;

import com.Majstro.psmsbackend.Models.Dtos.ProjectDto;
import com.Majstro.psmsbackend.Models.Dtos.UserWithRole;
import com.Majstro.psmsbackend.Models.EntityModels.Project;
import com.Majstro.psmsbackend.Models.EntityModels.ProjectAssignment;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

    public static ProjectDto ProjectToProjectDto(Project project){

        ProjectDto newProjectDto = new ProjectDto();
        List<UserWithRole> userRoleList = new ArrayList<>();

        newProjectDto.projectId=project.getId();
        newProjectDto.projectName=project.getName();
        newProjectDto.description=project.getDescription();
        newProjectDto.clientReference=project.getClientReference();
        newProjectDto.iconUrl=project.getIconUrl();
        newProjectDto.clientReference=project.getClientReference();

        for(ProjectAssignment y: project.getAssignments()){

            userRoleList.add(new UserWithRole(
                    y.getUser().getId(),
                    y.getRole().getRoleName(),
                    y.getId()));
        }

        newProjectDto.userRoleList=userRoleList;

        return newProjectDto;
    }

    public static void ProjectDtoToProjectPartiallyExtractor(ProjectDto projectDto,Project project){

        project.setName(projectDto.projectName);
        project.setDescription(projectDto.description);
        project.setClientReference(projectDto.clientReference);
        project.setIconUrl(projectDto.iconUrl);
        project.setArtifactCount(projectDto.artifactCount);

    }

}
