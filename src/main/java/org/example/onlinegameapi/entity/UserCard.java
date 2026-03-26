package org.example.onlinegameapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_cards")
@Getter
@Setter
@NoArgsConstructor
public class UserCard {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @Setter(AccessLevel.NONE)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @PrePersist
    private void prePersist(){
        this.id = UUID.randomUUID();
        this.amount = 1;
    }
}
