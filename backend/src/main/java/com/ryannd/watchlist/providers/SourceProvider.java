package com.ryannd.watchlist.providers;

import com.ryannd.watchlist.features.search.model.SearchResult;
import java.util.List;

public interface SourceProvider {
  List<SearchResult> searchByQuery(String query, String page);
}
