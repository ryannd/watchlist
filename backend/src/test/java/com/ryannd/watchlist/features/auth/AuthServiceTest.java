package com.ryannd.watchlist.features.auth;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class AuthServiceTest {

  @Mock private FirebaseAuth firebaseAuth;

  @Mock private UserRecord userRecord;

  @Mock private FirebaseToken firebaseToken;

  private AuthService authService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    authService = new AuthService(firebaseAuth);
  }

  @Test
  void createFirebaseUser_shouldReturnUid_whenUserCreatedSuccessfully()
      throws FirebaseAuthException {
    String email = "test@example.com";
    String password = "password123";
    String expectedUid = "firebase-uid-123";

    when(firebaseAuth.createUser(any(CreateRequest.class))).thenReturn(userRecord);
    when(userRecord.getUid()).thenReturn(expectedUid);

    String actualUid = authService.createFirebaseUser(email, password);

    assertEquals(expectedUid, actualUid);
    verify(firebaseAuth).createUser(any(CreateRequest.class));
  }

  @Test
  void createFirebaseUser_shouldThrowException_whenFirebaseAuthFails()
      throws FirebaseAuthException {
    String email = "test@example.com";
    String password = "password123";

    when(firebaseAuth.createUser(any(CreateRequest.class))).thenThrow(FirebaseAuthException.class);

    assertThrows(
        FirebaseAuthException.class, () -> authService.createFirebaseUser(email, password));
  }

  @Test
  void getUserById_shouldReturnUserRecord_whenUserExists() throws FirebaseAuthException {
    String uid = "firebase-uid-123";

    when(firebaseAuth.getUser(uid)).thenReturn(userRecord);

    UserRecord result = authService.getUserById(uid);

    assertNotNull(result);
    assertEquals(userRecord, result);
    verify(firebaseAuth).getUser(uid);
  }

  @Test
  void getUserById_shouldThrowException_whenUserNotFound() throws FirebaseAuthException {
    String uid = "non-existent-uid";

    when(firebaseAuth.getUser(uid)).thenThrow(FirebaseAuthException.class);

    assertThrows(FirebaseAuthException.class, () -> authService.getUserById(uid));
  }

  @Test
  void deleteUser_shouldCallFirebaseAuthDeleteUser() throws FirebaseAuthException {
    String uid = "firebase-uid-123";

    authService.deleteUser(uid);

    verify(firebaseAuth).deleteUser(uid);
  }

  @Test
  void deleteUser_shouldThrowException_whenUserNotFound() throws FirebaseAuthException {
    String uid = "non-existent-uid";

    doThrow(FirebaseAuthException.class).when(firebaseAuth).deleteUser(uid);

    assertThrows(FirebaseAuthException.class, () -> authService.deleteUser(uid));
  }

  @Test
  void verifyIdToken_shouldReturnUid_whenTokenIsValid() throws FirebaseAuthException {
    String idToken = "valid-id-token";
    String expectedUid = "firebase-uid-123";

    when(firebaseAuth.verifyIdToken(idToken)).thenReturn(firebaseToken);
    when(firebaseToken.getUid()).thenReturn(expectedUid);

    String result = authService.verifyIdToken(idToken);

    assertNotNull(result);
    assertEquals(expectedUid, result);
    verify(firebaseAuth).verifyIdToken(idToken);
  }

  @Test
  void verifyIdToken_shouldThrowException_whenTokenIsInvalid() throws FirebaseAuthException {
    String idToken = "invalid-token";

    when(firebaseAuth.verifyIdToken(idToken)).thenThrow(FirebaseAuthException.class);

    assertThrows(FirebaseAuthException.class, () -> authService.verifyIdToken(idToken));
  }
}
