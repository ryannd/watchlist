package com.ryannd.watchlist.media;

import com.ryannd.watchlist.media.provider.MediaProvider;
import java.util.List;

public class MediaService {
  private final MediaProvider provider;

  public MediaService(MediaProvider provider) {
    this.provider = provider;
  }

  public List<MediaItem> searchByQuery(String query) {
    return provider.searchByQuery(query);
  }
}
