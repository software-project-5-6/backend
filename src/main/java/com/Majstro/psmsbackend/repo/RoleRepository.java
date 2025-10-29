package com.Majstro.psmsbackend.repo;

import com.Majstro.psmsbackend.models.EntityModels.Role;
import com.Majstro.psmsbackend.models.Dtos.ProjecRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(ProjecRoles role);
}
