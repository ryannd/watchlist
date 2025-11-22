package com.ryannd.watchlist.features.media;

import com.ryannd.watchlist.features.media.metadata.MovieMetadata;
import com.ryannd.watchlist.features.media.metadata.ShowMetadata;
import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import com.ryannd.watchlist.features.media.repository.MediaRepository;
import com.ryannd.watchlist.providers.SourceProviderRegistry;
import com.ryannd.watchlist.providers.SourceType;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class MediaService {
  private final SourceProviderRegistry providerRegistry;
  private final MediaRepository mediaRepository;

  public MediaService(SourceProviderRegistry providerRegistry, MediaRepository mediaRepository) {
    this.providerRegistry = providerRegistry;
    this.mediaRepository = mediaRepository;
  }

  private MovieResponse fetchMovie(String id, SourceType source) {
    return providerRegistry.get(source).getMovie(id);
  }

  private ShowResponse fetchShow(String id, SourceType source) {
    return providerRegistry.get(source).getShow(id);
  }

  public MediaEntity getMedia(MediaType type, SourceType source, String id) {
    Optional<MediaEntity> existingMedia = mediaRepository.findBySourceAndSourceId(source, id);
    if (existingMedia.isPresent()) {
      return existingMedia.get();
    }
    return createMedia(type, source, id);
  }

  public MediaEntity createMedia(MediaType type, SourceType source, String id) {
    switch (type) {
      case MOVIE:
        return createMovie(source, id);
      case SHOW:
        return createShow(source, id);
      default:
        throw new IllegalArgumentException("Unsupported media type: " + type);
    }
  }

  public MediaEntity createMovie(SourceType source, String id) {
    MovieResponse response = this.fetchMovie(id, source);
    MediaEntity newMovie = new MediaEntity();
    MovieMetadata metadata =
        new MovieMetadata(
            response.getDescription(),
            response.getBackgroundPath(),
            response.getPosterPath(),
            response.getGenres(),
            response.getRuntime(),
            response.getReleaseDate());
    newMovie.setMetadata(metadata);
    newMovie.setSource(source);
    newMovie.setTitle(response.getTitle());
    newMovie.setType(MediaType.MOVIE);
    newMovie.setSourceId(id);
    return mediaRepository.save(newMovie);
  }

  public MediaEntity createShow(SourceType source, String id) {
    ShowResponse response = this.fetchShow(id, source);
    MediaEntity newShow = new MediaEntity();
    ShowMetadata metadata =
        new ShowMetadata(
            response.getDescription(),
            response.getBackgroundPath(),
            response.getPosterPath(),
            response.getGenres(),
            response.getSeasons(),
            response.getFirstAirDate(),
            response.isAiring());
    newShow.setMetadata(metadata);
    newShow.setSource(source);
    newShow.setTitle(response.getTitle());
    newShow.setType(MediaType.SHOW);
    newShow.setSourceId(id);
    return mediaRepository.save(newShow);
  }
}
