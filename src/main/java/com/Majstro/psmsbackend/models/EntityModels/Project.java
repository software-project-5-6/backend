package com.Majstro.psmsbackend.models.EntityModels;

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
    private String clientName;

    @Column
    private String clientAddress;

    @Column
    private String clientEmail;

    @Column
    private String clientPhone;

    @Column
    private String iconUrl;

    @Column
    private int artifactCount=0;

    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
    private List<ProjectAssignment> assignments = new ArrayList<>();


    public Project(String name,
                   String description,
                   String clientName,
                   String clientAddress,
                   String clientEmail,
                   String clientPhone,
                   String iconUrl,
                   int artifactCount) {
        this.name = name;
        this.description = description;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.iconUrl = iconUrl;
        this.artifactCount = artifactCount;
    }

}
