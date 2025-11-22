package com.ryannd.watchlist.features.media;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import com.ryannd.watchlist.features.media.repository.MediaRepository;
import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.SourceProviderRegistry;
import com.ryannd.watchlist.providers.SourceType;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MediaServiceTest {
  private SourceProvider mockProvider;
  private SourceProviderRegistry mockRegistry;
  private MediaRepository mockRepository;

  @BeforeEach
  void setUp() {
    mockProvider = Mockito.mock(SourceProvider.class);
    mockRegistry = Mockito.mock(SourceProviderRegistry.class);
    mockRepository = Mockito.mock(MediaRepository.class);

    MovieResponse movie = new MovieResponse("Movie", "description", "", "", null, null, null);
    ShowResponse show =
        new ShowResponse("Show", "description", null, null, null, null, null, false);

    when(mockRegistry.get(any(SourceType.class))).thenReturn(mockProvider);
    when(mockProvider.getMovie("1234")).thenReturn(movie);
    when(mockProvider.getShow("1234")).thenReturn(show);
    when(mockRepository.findBySourceAndSourceId(any(SourceType.class), any(String.class)))
        .thenReturn(Optional.empty());
    when(mockRepository.save(any(MediaEntity.class)))
        .thenAnswer(invocation -> invocation.getArgument(0));
  }

  @Test
  void getMovie_shouldReturnMovieResponse() {
    MediaService service = new MediaService(mockRegistry, mockRepository);

    MediaEntity media = service.getMedia(MediaType.MOVIE, SourceType.TMDB, "1234");

    assertNotNull(media);
    assertTrue(media.getTitle().equals("Movie"));
  }

  @Test
  void getShow_shouldReturnShowResponse() {
    MediaService service = new MediaService(mockRegistry, mockRepository);

    MediaEntity media = service.getMedia(MediaType.SHOW, SourceType.TMDB, "1234");

    assertNotNull(media);
    assertTrue(media.getTitle().equals("Show"));
  }
}
