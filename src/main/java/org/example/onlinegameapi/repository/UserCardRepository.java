package org.example.onlinegameapi.repository;

import org.example.onlinegameapi.entity.UserCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserCardRepository extends JpaRepository<UserCard, UUID> {
    @Query("""
        SELECT COUNT(uc) > 0 FROM UserCard uc
        WHERE uc.user.id = :userId
        AND uc.card.id = :cardId""")
    boolean checkUserCardExisting(
            @Param("userId") UUID userId,
            @Param("cardId") UUID cardId
    );

    UserCard getByCardId(UUID cardId);
    List<UserCard> findAllByUserId(UUID userId);
}
