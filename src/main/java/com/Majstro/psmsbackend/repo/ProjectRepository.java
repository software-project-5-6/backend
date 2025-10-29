package com.Majstro.psmsbackend.repo;

import com.Majstro.psmsbackend.models.EntityModels.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
