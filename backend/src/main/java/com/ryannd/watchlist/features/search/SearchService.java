package com.ryannd.watchlist.features.search;

import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class SearchService {
  private final SourceProvider sourceProvider;

  public SearchService(SourceProvider sourceProvider) {
    this.sourceProvider = sourceProvider;
  }

  public List<SearchResult> search(String query, String page) {
    return sourceProvider.searchByQuery(query, page);
  }
}
