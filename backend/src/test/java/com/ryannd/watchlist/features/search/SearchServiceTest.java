package com.ryannd.watchlist.features.search;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ryannd.watchlist.features.search.dto.SearchResponseDto;
import com.ryannd.watchlist.features.search.dto.SearchResultDto;
import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.SourceProviderRegistry;
import com.ryannd.watchlist.providers.SourceType;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SearchServiceTest {
  @Mock private SourceProvider mockProvider;
  @Mock private SourceProviderRegistry mockRegistry;
  @InjectMocks private SearchService service;

  @BeforeEach
  void setUp() {
    when(mockRegistry.get(any(SourceType.class))).thenReturn(mockProvider);
  }

  @Test
  void search_shouldReturnResults() {
    var pageOne =
        new SearchResponseDto(
            List.of(
                new SearchResultDto(
                    123, "Inception", "overview", "backdrop", "poster", "movie", "2000")),
            1,
            2);
    when(mockProvider.searchByQuery("Inception", "1")).thenReturn(pageOne);

    SearchResponseDto response = service.search("Inception", 1, SourceType.TMDB);

    assertNotNull(response);
    assertFalse(response.results().isEmpty());
    assertEquals("Inception", response.results().get(0).title());
  }

  @Test
  void search_shouldReturnCorrectPage() {
    var pageTwo =
        new SearchResponseDto(
            List.of(
                new SearchResultDto(
                    456, "Page2", "overview", "backdrop", "poster", "movie", "2000")),
            2,
            2);

    when(mockProvider.searchByQuery("Inception", "2")).thenReturn(pageTwo);
    SearchResponseDto response = service.search("Inception", 2, SourceType.TMDB);

    assertNotNull(response);
    assertEquals(2, response.currentPage());
    assertEquals("Page2", response.results().get(0).title());
  }
}
