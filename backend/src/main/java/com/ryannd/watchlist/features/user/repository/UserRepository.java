package com.ryannd.watchlist.features.user.repository;

import com.ryannd.watchlist.features.user.model.UserEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
  Optional<UserEntity> findByFirebaseUid(String firebaseUid);
}
