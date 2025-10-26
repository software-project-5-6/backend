package com.Majstro.psmsbackend.Models.EntityModels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Data
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(length = 1000)
    private String description;

    @Column
    private String clientReference;

    @Column
    private String iconUrl;

    @Column
    private int artifactCount=0;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
    private List<ProjectAssignment> assignments = new ArrayList<>();


    public Project(String name, String description, String clientReference, String iconUrl, int artifactCount) {
        this.name = name;
        this.description = description;
        this.clientReference = clientReference;
        this.iconUrl = iconUrl;
        this.artifactCount = artifactCount;
    }

}
