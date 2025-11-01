package com.Majstro.psmsbackend.mapper;

import com.Majstro.psmsbackend.models.Dtos.ProjectDto;
import com.Majstro.psmsbackend.models.Dtos.UserWithRole;
import com.Majstro.psmsbackend.models.EntityModels.Project;
import com.Majstro.psmsbackend.models.EntityModels.ProjectAssignment;

import java.util.ArrayList;
import java.util.List;

public class ProjectMapper {

    public static ProjectDto ProjectToProjectDto(Project project){

        ProjectDto newProjectDto = new ProjectDto();
        List<UserWithRole> userRoleList = new ArrayList<>();

        newProjectDto.projectId=project.getId();
        newProjectDto.projectName=project.getName();
        newProjectDto.description=project.getDescription();
        newProjectDto.clientName=project.getClientName();
        newProjectDto.clientAddress=project.getClientAddress();
        newProjectDto.clientEmail=project.getClientEmail();
        newProjectDto.clientPhone=project.getClientPhone();

        newProjectDto.iconUrl=project.getIconUrl();


        for(ProjectAssignment y: project.getAssignments()){

            userRoleList.add(new UserWithRole(
                    y.getUser().getId(),
                    y.getRole(),
                    y.getId()));
        }

        newProjectDto.userRoleList=userRoleList;

        return newProjectDto;
    }

    public static void ProjectDtoToProjectPartiallyExtractor(ProjectDto projectDto,Project project){

        project.setName(projectDto.projectName);
        project.setDescription(projectDto.description);
        project.setClientName(projectDto.clientName);
        project.setClientAddress(projectDto.clientAddress);
        project.setClientEmail(projectDto.clientEmail);
        project.setClientPhone(projectDto.clientPhone);
        project.setIconUrl(projectDto.iconUrl);
        project.setArtifactCount(projectDto.artifactCount);

    }

}
