package com.ryannd.watchlist.providers.tmdb;

import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResponse;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TmdbProvider implements SourceProvider {
  private final TmdbClient tmdbClient;

  public TmdbProvider(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  @Override
  public SearchResponse searchByQuery(String query, String page) {
    TmdbSearchResponse tmdbResponse = tmdbClient.search(query, page).block();
    List<SearchResult> results =
        tmdbResponse.getResults().stream()
            .filter(r -> !"person".equalsIgnoreCase(r.getMediaType()))
            .map(this::mapToSearchResult)
            .collect(Collectors.toList());

    SearchResponse response =
        new SearchResponse(results, tmdbResponse.getPage(), tmdbResponse.getTotalPages());
    return response;
  }

  private SearchResult mapToSearchResult(TmdbSearchResult result) {
    return new SearchResult(
        result.getId(),
        result.getTitle(),
        result.getOverview(),
        result.getBackdropPath(),
        result.getPosterPath(),
        result.getMediaType(),
        result.getReleaseDate());
  }
}
