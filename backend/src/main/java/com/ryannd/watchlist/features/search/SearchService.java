package com.ryannd.watchlist.features.search;

import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.providers.SourceProviderRegistry;
import com.ryannd.watchlist.providers.SourceType;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
  private final SourceProviderRegistry providerRegistry;

  public SearchService(SourceProviderRegistry providerRegistry) {
    this.providerRegistry = providerRegistry;
  }

  public SearchResponse search(String query, int page, SourceType sourceType) {
    return providerRegistry.get(sourceType).searchByQuery(query, String.valueOf(page));
  }
}
