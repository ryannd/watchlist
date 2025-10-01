package com.ryannd.watchlist.provider;

import com.ryannd.watchlist.media.MediaItem;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TmdbProvider implements MediaProvider {
  @Value("${tmdb.api.key}")
  private String apiKey;

  @Override
  public List<MediaItem> searchByQuery(String query) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String getProviderName() {
    return "TMDB";
  }
}
