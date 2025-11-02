package com.Majstro.psmsbackend.models.EntityModels;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
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
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL, orphanRemoval = true,fetch=FetchType.LAZY)
    private List<ProjectAssignment> assignments = new ArrayList<>();


    public User(String cognitoSub, String username, String email) {
        this.cognitoSub = cognitoSub;
        this.username = username;
        this.email = email;
    }

    @PrePersist
    protected void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return id != null && id.equals(user.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", cognitoSub='" + cognitoSub + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", globalRole=" + globalRole +
                '}';
    }
}
