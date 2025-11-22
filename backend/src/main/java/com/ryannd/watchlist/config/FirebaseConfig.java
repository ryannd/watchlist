package com.ryannd.watchlist.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FirebaseConfig {
  @Value("${firebase.credentials.path:classpath:serviceAccountKey.json}")
  private Resource privateKey;

  @Bean
  public FirebaseApp firebaseApp() throws IOException {
    if (FirebaseApp.getApps().isEmpty()) {
      FirebaseOptions firebaseOptions =
          FirebaseOptions.builder()
              .setCredentials(GoogleCredentials.fromStream(privateKey.getInputStream()))
              .build();
      return FirebaseApp.initializeApp(firebaseOptions);
    }
    return FirebaseApp.getInstance();
  }

  @Bean
  public FirebaseAuth firebaseAuth(FirebaseApp firebaseApp) {
    return FirebaseAuth.getInstance(firebaseApp);
  }
}
