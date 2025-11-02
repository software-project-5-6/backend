package com.Majstro.psmsbackend.models.Dtos;

import com.Majstro.psmsbackend.models.EntityModels.GlobalRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID id;
    private String cognitoSub;
    private String username;
    private String email;
    private GlobalRole globalRole;
    private List<ProjectWithRole> assignments = new ArrayList<>();

    public UserDto(UUID id, String cognitoSub, String username, String email, GlobalRole globalRole) {
        this.id = id;
        this.cognitoSub = cognitoSub;
        this.username = username;
        this.email = email;
        this.globalRole = globalRole;
    }
}
