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

        newProjectDto.setProjectId(project.getId());
        newProjectDto.setProjectName(project.getName());
        newProjectDto.setDescription(project.getDescription());
        newProjectDto.setClientName(project.getClientName());
        newProjectDto.setClientAddress(project.getClientAddress());
        newProjectDto.setClientEmail(project.getClientEmail());
        newProjectDto.setClientPhone(project.getClientPhone());
        newProjectDto.setIconUrl(project.getIconUrl());
        newProjectDto.setArtifactCount(project.getArtifactCount());

        for(ProjectAssignment y: project.getAssignments()){
            userRoleList.add(new UserWithRole(
                    y.getUser().getId(),
                    y.getRole(),
                    y.getId()));
        }

        newProjectDto.setUserRoleList(userRoleList);

        return newProjectDto;
    }

    public static void ProjectDtoToProjectPartiallyExtractor(ProjectDto projectDto, Project project){

        project.setName(projectDto.getProjectName());
        project.setDescription(projectDto.getDescription());
        project.setClientName(projectDto.getClientName());
        project.setClientAddress(projectDto.getClientAddress());
        project.setClientEmail(projectDto.getClientEmail());
        project.setClientPhone(projectDto.getClientPhone());
        project.setIconUrl(projectDto.getIconUrl());
        project.setArtifactCount(projectDto.getArtifactCount());
    }
}
