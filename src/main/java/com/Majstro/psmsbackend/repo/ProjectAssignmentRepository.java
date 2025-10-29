package com.Majstro.psmsbackend.repo;

import com.Majstro.psmsbackend.models.EntityModels.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, UUID> {
}
