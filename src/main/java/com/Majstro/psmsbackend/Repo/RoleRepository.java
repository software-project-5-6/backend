package com.Majstro.psmsbackend.Repo;

import com.Majstro.psmsbackend.Models.EntityModels.Role;
import com.Majstro.psmsbackend.Models.Dtos.ProjecRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByRoleName(ProjecRoles role);
}
