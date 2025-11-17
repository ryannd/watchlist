package com.ryannd.watchlist.features.media;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import com.ryannd.watchlist.providers.SourceProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MediaServiceTest {
  private SourceProvider mockProvider;

  @BeforeEach
  void setUp() {
    mockProvider = Mockito.mock(SourceProvider.class);

    MovieResponse movie = new MovieResponse("Movie", "description", "", "", null, null, null);
    ShowResponse show =
        new ShowResponse("Show", "description", null, null, null, null, null, false);

    when(mockProvider.getMovie("1234")).thenReturn(movie);
    when(mockProvider.getShow("1234")).thenReturn(show);
  }

  @Test
  void getMovie_shouldReturnMovieResponse() {
    MediaService service = new MediaService(mockProvider);

    MovieResponse response = service.getMovie("1234");

    assertNotNull(response);
    assertTrue(response.getTitle().equals("Movie"));
  }

  @Test
  void getShow_shouldReturnShowResponse() {
    MediaService service = new MediaService(mockProvider);

    ShowResponse response = service.getShow("1234");

    assertNotNull(response);
    assertTrue(response.getTitle().equals("Show"));
  }
}
