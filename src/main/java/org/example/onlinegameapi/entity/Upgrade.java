package org.example.onlinegameapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.onlinegameapi.converter.UpgradeTypeConverter;
import org.example.onlinegameapi.enums.UpgradeType;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "upgrades")
@Getter
@Setter
@NoArgsConstructor
public class Upgrade {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "upgrade_type", nullable = false)
    @Convert(converter = UpgradeTypeConverter.class)
    private UpgradeType upgradeType;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Integer price;

    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
    }
}
