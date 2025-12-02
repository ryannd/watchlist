package com.ryannd.watchlist.features.user;

import com.ryannd.watchlist.features.user.model.UserEntity;
import com.ryannd.watchlist.features.user.repository.UserRepository;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
  private final UserRepository userRepository;

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public UserEntity getOrCreateUser(String firebaseUid) {
    Optional<UserEntity> existingUser = userRepository.findByFirebaseUid(firebaseUid);

    if (existingUser.isPresent()) {
      return existingUser.get();
    }

    UserEntity newUser = new UserEntity();
    newUser.setFirebaseUid(firebaseUid);
    return userRepository.save(newUser);
  }

  public UserEntity getAuthenticatedUser() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication == null || !authentication.isAuthenticated()) {
      throw new IllegalStateException("No authenticated user");
    }

    Object principal = authentication.getPrincipal();
    if (!(principal instanceof String firebaseUid)) {
      throw new IllegalStateException("Unexpected principal type: " + principal);
    }

    return getOrCreateUser(firebaseUid);
  }
}
