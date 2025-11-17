package com.ryannd.watchlist.providers;

import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import com.ryannd.watchlist.features.search.model.SearchResponse;

public interface SourceProvider {
  SearchResponse searchByQuery(String query, String page);

  MovieResponse getMovie(String id);

  ShowResponse getShow(String id);
}
