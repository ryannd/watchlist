package com.ryannd.watchlist.features.search;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SearchServiceTests {
  private SourceProvider mockProvider;

  @BeforeEach
  void setUp() {
    mockProvider = Mockito.mock(SourceProvider.class);

    SearchResponse pageOne =
        new SearchResponse(
            List.of(
                new SearchResult(
                    123, "Inception", "overview", "backdrop", "poster", "movie", "2000")),
            1,
            2);

    SearchResponse pageTwo =
        new SearchResponse(
            List.of(
                new SearchResult(456, "Page2", "overview", "backdrop", "poster", "movie", "2000")),
            2,
            2);

    when(mockProvider.searchByQuery("Inception", "1")).thenReturn(pageOne);
    when(mockProvider.searchByQuery("Inception", "2")).thenReturn(pageTwo);
    when(mockProvider.searchByQuery(anyString(), anyString())).thenAnswer(inv -> pageTwo);
  }

  @Test
  void searchByQuery_shouldReturnResults() {
    SearchService service = new SearchService(mockProvider);

    SearchResponse response = service.search("Inception", "1");

    assertNotNull(response);
    assertFalse(response.getResults().isEmpty());
    assertTrue(response.getResults().stream().anyMatch(r -> r.getTitle().equals("Inception")));
  }

  @Test
  void searchByQuery_shouldReturnPage() {
    SearchService service = new SearchService(mockProvider);

    SearchResponse response = service.search("Inception", "2");

    assertNotNull(response);
    assertFalse(response.getResults().isEmpty());
    assertTrue(response.getCurrentPage() == 2);
  }
}
