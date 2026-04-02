package org.example.onlinegameapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlinegameapi.enums.Rarity;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "cards")
@Getter
@Setter
@NoArgsConstructor
public class Card {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "rarity", nullable = false)
    private Rarity rarity;

    @Column(name = "icon_url")
    private String iconUrl;

    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
    }
}
