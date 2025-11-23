package com.ryannd.watchlist.features.media;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ryannd.watchlist.features.media.dto.MovieDto;
import com.ryannd.watchlist.features.media.dto.ShowDto;
import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.media.repository.MediaRepository;
import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.SourceProviderRegistry;
import com.ryannd.watchlist.providers.SourceType;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class MediaServiceTest {
  private SourceProvider mockProvider;
  private SourceProviderRegistry mockRegistry;
  private MediaRepository mockRepository;
  private MediaService service;

  @BeforeEach
  void setUp() {
    mockProvider = Mockito.mock(SourceProvider.class);
    mockRegistry = Mockito.mock(SourceProviderRegistry.class);
    mockRepository = Mockito.mock(MediaRepository.class);
    service = new MediaService(mockRegistry, mockRepository);

    when(mockRegistry.get(any(SourceType.class))).thenReturn(mockProvider);
    when(mockRepository.findBySourceAndSourceId(any(), any())).thenReturn(Optional.empty());
    when(mockRepository.save(any(MediaEntity.class))).thenAnswer(inv -> inv.getArgument(0));
  }

  @Test
  void getOrCreateMedia_createsMovie() {
    MovieDto movieDto =
        new MovieDto(
            "Inception",
            "A mind-bending thriller",
            "/poster.jpg",
            "/backdrop.jpg",
            List.of("Sci-Fi"),
            148,
            "2010-07-16");
    when(mockProvider.fetchMedia(MediaType.MOVIE, "123")).thenReturn(movieDto);

    MediaEntity result = service.getOrCreateMedia(MediaType.MOVIE, SourceType.TMDB, "123");

    assertNotNull(result);
    assertEquals("Inception", result.getTitle());
    assertEquals(MediaType.MOVIE, result.getType());
    verify(mockRepository).save(any(MediaEntity.class));
  }

  @Test
  void getOrCreateMedia_createsShow() {
    ShowDto showDto =
        new ShowDto(
            "Breaking Bad",
            "A high school chemistry teacher turned meth cook",
            "/poster.jpg",
            "/backdrop.jpg",
            List.of("Drama"),
            List.of(),
            "2008-01-20",
            false);
    when(mockProvider.fetchMedia(MediaType.SHOW, "456")).thenReturn(showDto);

    MediaEntity result = service.getOrCreateMedia(MediaType.SHOW, SourceType.TMDB, "456");

    assertNotNull(result);
    assertEquals("Breaking Bad", result.getTitle());
    assertEquals(MediaType.SHOW, result.getType());
  }

  @Test
  void getOrCreateMedia_returnsExisting() {
    MediaEntity existing = new MediaEntity();
    existing.setTitle("Existing");
    when(mockRepository.findBySourceAndSourceId(SourceType.TMDB, "999"))
        .thenReturn(Optional.of(existing));

    MediaEntity result = service.getOrCreateMedia(MediaType.MOVIE, SourceType.TMDB, "999");

    assertEquals("Existing", result.getTitle());
    verify(mockRepository, Mockito.never()).save(any());
  }
}
