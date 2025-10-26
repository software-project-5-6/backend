package com.Majstro.psmsbackend.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class UserWithRole {
    public UUID userId;
    public ProjecRoles role;
    public UUID assignmentId;
}
