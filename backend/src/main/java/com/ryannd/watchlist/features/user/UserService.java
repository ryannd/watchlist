package com.ryannd.watchlist.features.user;

import com.ryannd.watchlist.features.user.model.UserEntity;
import com.ryannd.watchlist.features.user.repository.UserRepository;
import java.util.Optional;
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
}
