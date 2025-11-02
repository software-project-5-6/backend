package com.Majstro.psmsbackend.models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private UUID projectId;
    private String projectName;
    private String description;
    private String clientName;
    private String clientAddress;
    private String clientEmail;
    private String clientPhone;
    private String iconUrl;
    private Integer artifactCount;
    private List<UserWithRole> userRoleList = new ArrayList<>();
}
