package com.ryannd.watchlist.providers;

import com.ryannd.watchlist.features.media.dto.media.MediaDto;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.search.dto.SearchResponseDto;

public interface SourceProvider {
  SourceType getSourceType();

  SearchResponseDto searchByQuery(String query, String page);

  MediaDto fetchMedia(MediaType type, String id);
}
