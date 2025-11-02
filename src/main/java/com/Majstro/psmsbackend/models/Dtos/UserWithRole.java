package com.Majstro.psmsbackend.models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithRole {
    private UUID userId;
    private ProjectRoles role;
    private UUID assignmentId;
}
