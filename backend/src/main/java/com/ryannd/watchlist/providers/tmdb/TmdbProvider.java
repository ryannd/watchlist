package com.ryannd.watchlist.providers.tmdb;

import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResult;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TmdbProvider implements SourceProvider {
  private final TmdbClient tmdbClient;

  public TmdbProvider(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<TmdbSearchResult> searchByQuery(String query) {
    return tmdbClient.search(query).block().getResults();
  }
}
