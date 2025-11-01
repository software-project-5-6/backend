package com.Majstro.psmsbackend.models.EntityModels;

import com.Majstro.psmsbackend.models.Dtos.ProjecRoles;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "project_assignments")
@Data
public class ProjectAssignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_role")
    private ProjecRoles role;

    private LocalDateTime assignedAt = LocalDateTime.now();
}
