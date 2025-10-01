package com.ryannd.watchlist.media;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ryannd.watchlist.provider.MediaProvider;
import java.util.List;
import org.junit.jupiter.api.Test;

class MediaServiceTest {
  private final MediaProvider mockProvider =
      new MediaProvider() {
        @Override
        public List<MediaItem> searchByQuery(String query) {
          return List.of(new MediaItem("1", "Inception", "movie", "mock", "mock1"));
        }

        @Override
        public String getProviderName() {
          return "mock";
        }
      };

  @Test
  void searchByQuery_shouldReturnMatchingTitles() {
    MediaService mediaService = new MediaService(this.mockProvider);

    List<MediaItem> results = mediaService.searchByQuery("Inception");

    assertNotNull(results);
    assertTrue(results.stream().anyMatch(item -> item.getTitle().equals("Inception")));
  }
}
