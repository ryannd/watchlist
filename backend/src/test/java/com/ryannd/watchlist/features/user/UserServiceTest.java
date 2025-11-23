package com.ryannd.watchlist.features.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ryannd.watchlist.features.user.model.UserEntity;
import com.ryannd.watchlist.features.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
  @Mock private UserRepository mockRepository;
  @InjectMocks private UserService mockService;

  @Test
  void getOrCreateUser_shouldReturnAnExistingUser() {
    UserEntity mockUser = new UserEntity(Long.valueOf(1234), "1234");
    when(mockRepository.findByFirebaseUid("1234")).thenReturn(Optional.of(mockUser));

    UserEntity result = mockService.getOrCreateUser("1234");

    assertEquals(Long.valueOf(1234), result.getId());
    verify(mockRepository, never()).save(any(UserEntity.class));
  }

  @Test
  void getOrCreateUser_shouldCreateANewUser() {
    when(mockRepository.findByFirebaseUid("4567")).thenReturn(Optional.empty());
    UserEntity newUser = new UserEntity(Long.valueOf(4567), "4567");
    when(mockRepository.save(any(UserEntity.class))).thenReturn(newUser);

    UserEntity result = mockService.getOrCreateUser("4567");

    assertNotNull(result);
    assertEquals("4567", result.getFirebaseUid());
    verify(mockRepository).save(any(UserEntity.class));
  }
}
