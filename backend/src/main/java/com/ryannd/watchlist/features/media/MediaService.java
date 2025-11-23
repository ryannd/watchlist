package com.ryannd.watchlist.features.media;

import com.ryannd.watchlist.features.media.dto.media.MediaDto;
import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.media.repository.MediaRepository;
import com.ryannd.watchlist.providers.SourceProvider;
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

  public MediaEntity getOrCreateMedia(MediaType type, SourceType source, String sourceId) {
    return mediaRepository
        .findBySourceAndSourceId(source, sourceId)
        .orElseGet(() -> createMedia(type, source, sourceId));
  }

  private MediaEntity createMedia(MediaType type, SourceType source, String sourceId) {
    SourceProvider provider = providerRegistry.get(source);
    MediaDto dto = provider.fetchMedia(type, sourceId);

    MediaEntity media = new MediaEntity();
    media.setTitle(dto.title());
    media.setType(type);
    media.setSource(source);
    media.setSourceId(sourceId);
    media.setMetadata(dto.toMetadata());

    return mediaRepository.save(media);
  }

  public Optional<MediaEntity> findBySourceAndId(SourceType source, String sourceId) {
    return mediaRepository.findBySourceAndSourceId(source, sourceId);
  }
}
