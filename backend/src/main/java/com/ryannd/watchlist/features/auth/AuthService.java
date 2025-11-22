package com.ryannd.watchlist.features.auth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.auth.UserRecord.CreateRequest;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final FirebaseAuth firebaseAuth;

  public AuthService(FirebaseAuth firebaseAuth) {
    this.firebaseAuth = firebaseAuth;
  }

  public String createFirebaseUser(String email, String password) throws FirebaseAuthException {
    CreateRequest request = new CreateRequest().setEmail(email).setPassword(password);

    UserRecord userRecord = firebaseAuth.createUser(request);
    return userRecord.getUid();
  }

  public UserRecord getUserById(String uid) throws FirebaseAuthException {
    return firebaseAuth.getUser(uid);
  }

  public void deleteUser(String uid) throws FirebaseAuthException {
    firebaseAuth.deleteUser(uid);
  }

  public String verifyIdToken(String idToken) throws FirebaseAuthException {
    return firebaseAuth.verifyIdToken(idToken).getUid();
  }
}
