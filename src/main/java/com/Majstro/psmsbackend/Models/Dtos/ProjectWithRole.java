package com.Majstro.psmsbackend.Models.Dtos;

import lombok.Data;

import java.util.UUID;

@Data
public class ProjectWithRole {

    public UUID projectId;
    public String projectName;
    public ProjecRoles projectRole;
}
