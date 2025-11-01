package com.Majstro.psmsbackend.models.Dtos;

import java.util.List;
import java.util.UUID;



public class ProjectDto {
    public UUID projectId ;
    public String projectName;
    public String description;
    public String clientName;
    public String clientAddress;
    public String clientEmail;
    public String clientPhone;
    public String iconUrl;
    public int artifactCount;
    public  List<UserWithRole> userRoleList;
}
