package com.ryannd.watchlist.media.provider;

import com.ryannd.watchlist.media.MediaItem;
import com.ryannd.watchlist.media.client.TmdbClient;
import com.ryannd.watchlist.media.model.TmdbSearchResult;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TmdbProvider implements MediaProvider {
  private final TmdbClient tmdbClient;

  public TmdbProvider(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  @Override
  public List<MediaItem> searchByQuery(String query) {
    return tmdbClient
        .search(query)
        .block() // blocking since your interface returns List<MediaItem>
        .getResults()
        .stream()
        .map(this::mapToMediaItem)
        .collect(Collectors.toList());
  }

  @Override
  public String getProviderName() {
    return "TMDB";
  }

  private MediaItem mapToMediaItem(TmdbSearchResult tmdbItem) {
    return new MediaItem(
        String.valueOf(tmdbItem.getId()),
        tmdbItem.getTitle(),
        tmdbItem.getMediaType(),
        "tmdb",
        String.valueOf(tmdbItem.getId()));
  }
}
