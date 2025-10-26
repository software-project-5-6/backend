package com.Majstro.psmsbackend.Repo;

import com.Majstro.psmsbackend.Models.EntityModels.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {
}
