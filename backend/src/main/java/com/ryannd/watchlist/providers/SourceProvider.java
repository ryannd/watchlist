package com.ryannd.watchlist.providers;

import com.ryannd.watchlist.features.search.model.SearchResponse;

public interface SourceProvider {
  SearchResponse searchByQuery(String query, String page);
}
