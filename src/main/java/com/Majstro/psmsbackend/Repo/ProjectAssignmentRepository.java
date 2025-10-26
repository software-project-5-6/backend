package com.Majstro.psmsbackend.Repo;

import com.Majstro.psmsbackend.Models.EntityModels.ProjectAssignment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectAssignmentRepository extends JpaRepository<ProjectAssignment, UUID> {
}
