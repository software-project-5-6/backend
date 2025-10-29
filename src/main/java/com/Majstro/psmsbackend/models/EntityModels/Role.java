package com.Majstro.psmsbackend.models.EntityModels;

import com.Majstro.psmsbackend.models.Dtos.ProjecRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Data
@NoArgsConstructor
public class Role {

    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private ProjecRoles roleName;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProjectAssignment> assignments = new ArrayList<>();

}
