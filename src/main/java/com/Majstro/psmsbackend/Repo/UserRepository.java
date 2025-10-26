package com.Majstro.psmsbackend.Repo;

import com.Majstro.psmsbackend.Models.EntityModels.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByCognitoSub(String cognitoSub);
}
