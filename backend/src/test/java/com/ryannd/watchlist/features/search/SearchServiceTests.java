package com.ryannd.watchlist.features.search;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SearchServiceTests {
  private final SourceProvider mockProvider =
      new SourceProvider() {
        @Override
        public List<SearchResult> searchByQuery(String query, String page) {
          if (page.equals("2")) {
            return List.of(
                new SearchResult(456, "Page2", "overview", "backdrop", "poster", "movie", "2000"));
          }
          return List.of(
              new SearchResult(
                  123, "Inception", "overview", "backdrop", "poster", "movie", "2000"));
        }
      };

  @Test
  void searchByQuery_shouldReturnResults() {
    SearchService service = new SearchService(mockProvider);

    List<SearchResult> results = service.search("Inception", "1");

    assertNotNull(results);
    assertFalse(results.isEmpty());
    assertTrue(results.stream().anyMatch(r -> r.getTitle().equals("Inception")));
  }

  @Test
  void searchByQuery_shouldReturnPage() {
    SearchService service = new SearchService(mockProvider);

    List<SearchResult> results = service.search("Inception", "2");

    assertNotNull(results);
    assertFalse(results.isEmpty());
    assertTrue(results.stream().anyMatch(r -> r.getTitle().equals("Page2")));
  }
}
