package com.Majstro.psmsbackend.mapper;

import com.Majstro.psmsbackend.models.Dtos.ProjectWithRole;
import com.Majstro.psmsbackend.models.Dtos.UserDto;
import com.Majstro.psmsbackend.models.EntityModels.ProjectAssignment;
import com.Majstro.psmsbackend.models.EntityModels.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserDto UserToUserDto(User user){

        UserDto newuserdto=new UserDto(
                user.getId(),
                user.getCognitoSub(),
                user.getUsername(),
                user.getEmail(),
                user.getGlobalRole()) ;


        List<ProjectWithRole> newProjectRoleList = new ArrayList<>();

        for(ProjectAssignment x: user.getAssignments()){
            ProjectWithRole projectRolePair = new ProjectWithRole();

            projectRolePair.setProjectId(x.getId());
            projectRolePair.setProjectName(x.getProject().getName());
            projectRolePair.setProjectRole(x.getRole());


            newProjectRoleList.add(projectRolePair);
        }

        newuserdto.assignments=newProjectRoleList;

        return newuserdto;
    }


}
