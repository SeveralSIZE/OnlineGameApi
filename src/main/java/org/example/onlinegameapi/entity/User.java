package org.example.onlinegameapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlinegameapi.converter.RoleConverter;
import org.example.onlinegameapi.enums.Role;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "creation_date", updatable = false)
    @Setter(AccessLevel.NONE)
    private OffsetDateTime creationDate;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "role", nullable = false)
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "coins", nullable = false)
    private Integer coins;

    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
        this.creationDate = OffsetDateTime.now();
    }
}
