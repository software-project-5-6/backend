package com.Majstro.psmsbackend.models.Dtos;

import com.Majstro.psmsbackend.models.EntityModels.GlobalRole;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    public UUID id;
    public String cognitoSub;
    public String username;
    public String email;
    public GlobalRole globalRole;
    public List<ProjectWithRole> assignments = new ArrayList<>();


    public UserDto(UUID id, String cognitoSub, String username, String email, GlobalRole globalRole) {
        this.id = id;
        this.cognitoSub = cognitoSub;
        this.username = username;
        this.email = email;
        this.globalRole = globalRole;
    }

}
