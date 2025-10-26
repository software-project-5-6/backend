package com.Majstro.psmsbackend.Models.EntityModels;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "cognito_sub", nullable = false, unique = true)
    private String cognitoSub;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;


    @Enumerated(EnumType.STRING)
    @Column(name = "global_role")
    private GlobalRole globalRole;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
    private List<ProjectAssignment> assignments = new ArrayList<>();


    public User(String cognitoSub, String username, String email) {
        this.cognitoSub = cognitoSub;
        this.username = username;
        this.email = email;
    }
}

