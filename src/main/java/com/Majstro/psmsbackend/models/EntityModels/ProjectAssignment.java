package com.Majstro.psmsbackend.models.EntityModels;

import com.Majstro.psmsbackend.models.Dtos.ProjectRoles;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "project_assignments")
@Getter
@Setter
@NoArgsConstructor
public class ProjectAssignment {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_role")
    private ProjectRoles role;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    @PrePersist
    protected void onCreate() {
        if (assignedAt == null) {
            assignedAt = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProjectAssignment)) return false;
        ProjectAssignment that = (ProjectAssignment) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "ProjectAssignment{" +
                "id=" + id +
                ", role=" + role +
                ", assignedAt=" + assignedAt +
                '}';
    }
}
