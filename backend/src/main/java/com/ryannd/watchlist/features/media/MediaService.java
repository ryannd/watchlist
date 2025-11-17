package com.ryannd.watchlist.features.media;

import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import com.ryannd.watchlist.providers.SourceProvider;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
  private final SourceProvider sourceProvider;

  public MediaService(SourceProvider sourceProvider) {
    this.sourceProvider = sourceProvider;
  }

  public MovieResponse getMovie(String id) {
    return this.sourceProvider.getMovie(id);
  }

  public ShowResponse getShow(String id) {
    return this.sourceProvider.getShow(id);
  }
}
