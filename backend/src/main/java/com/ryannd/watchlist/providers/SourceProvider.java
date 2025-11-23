package com.ryannd.watchlist.providers;

import com.ryannd.watchlist.features.media.dto.media.MediaDto;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.search.model.SearchResponse;

public interface SourceProvider {
  SourceType getSourceType();

  SearchResponse searchByQuery(String query, String page);

  MediaDto fetchMedia(MediaType type, String id);
}
