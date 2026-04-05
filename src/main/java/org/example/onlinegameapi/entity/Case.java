package org.example.onlinegameapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "cases")
@Getter
@Setter
@NoArgsConstructor
public class Case {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "common_chance", nullable = false)
    private Float commonChance;

    @Column(name = "uncommon_chance", nullable = false)
    private Float uncommonChance;

    @Column(name = "rare_chance", nullable = false)
    private Float rareChance;

    @Column(name = "epic_chance", nullable = false)
    private Float epicChance;

    @Column(name = "legendary_chance", nullable = false)
    private Float legendaryChance;

    @Column(name = "eternal_chance", nullable = false)
    private Float eternalChance;

    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
    }
}
