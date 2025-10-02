package com.ryannd.watchlist.providers.tmdb;

import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class TmdbClient {
  @Value("${tmdb.api.key}")
  private String apiKey;

  private final WebClient webClient;

  public TmdbClient(WebClient tmdbWebClient) {
    this.webClient = tmdbWebClient;
  }

  public Mono<TmdbSearchResponse> search(String query) {
    return webClient
        .method(HttpMethod.GET)
        .uri(
            uriBuilder ->
                uriBuilder
                    .scheme("https")
                    .host("api.themoviedb.org")
                    .path("/3/search/multi")
                    .queryParam("query", query)
                    .queryParam("page", 1)
                    .build())
        .contentType(MediaType.APPLICATION_JSON)
        .header("Authorization", "Bearer " + apiKey)
        .retrieve()
        .bodyToMono(TmdbSearchResponse.class);
  }
}
