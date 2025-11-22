package com.ryannd.watchlist.features.media.repository;

import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.providers.SourceType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<MediaEntity, Long> {
  Optional<MediaEntity> findByTitleAndType(String title, String type);

  Optional<MediaEntity> findBySourceAndSourceId(SourceType source, String sourceId);

  boolean existsByTitleAndType(String title, String type);
}
