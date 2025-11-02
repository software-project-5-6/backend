package com.Majstro.psmsbackend.models.EntityModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
//dim
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
    private Integer artifactCount;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<ProjectAssignment> assignments = new ArrayList<>();

    public Project(String name,
                   String description,
                   String clientName,
                   String clientAddress,
                   String clientEmail,
                   String clientPhone,
                   String iconUrl,
                   Integer artifactCount) {
        this.name = name;
        this.description = description;
        this.clientName = clientName;
        this.clientAddress = clientAddress;
        this.clientEmail = clientEmail;
        this.clientPhone = clientPhone;
        this.iconUrl = iconUrl;
        this.artifactCount = artifactCount;
    }

    @PrePersist
    protected void onCreate() {
        if (artifactCount == null) {
            artifactCount = 0;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return id != null && id.equals(project.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
