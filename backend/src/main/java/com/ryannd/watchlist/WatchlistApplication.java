package com.ryannd.watchlist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Entry point for the Watchlist Spring Boot application. */
@SpringBootApplication
public final class WatchlistApplication {

  private WatchlistApplication() {}

  /**
   * Bootstraps the Spring application.
   *
   * @param args command line arguments
   */
  public static void main(final String[] args) {
    SpringApplication.run(WatchlistApplication.class, args);
  }
}
