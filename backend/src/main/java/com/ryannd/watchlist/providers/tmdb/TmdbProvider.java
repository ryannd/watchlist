package com.ryannd.watchlist.providers.tmdb;

import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
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
  public List<SearchResult> searchByQuery(String query) {
    return tmdbClient.search(query).block().getResults().stream()
        .map(this::mapToSearchResult)
        .collect(Collectors.toList());
  }

  private SearchResult mapToSearchResult(TmdbSearchResult result) {
    return new SearchResult();
  }
}
