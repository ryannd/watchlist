package com.ryannd.watchlist.features.search;

import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.providers.SourceProvider;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
  private final SourceProvider sourceProvider;

  public SearchService(SourceProvider sourceProvider) {
    this.sourceProvider = sourceProvider;
  }

  public SearchResponse search(String query, String page) {
    return sourceProvider.searchByQuery(query, page);
  }
}
