package com.ryannd.watchlist.features.media.repository;

import com.ryannd.watchlist.features.media.model.Media;
import com.ryannd.watchlist.providers.SourceType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media, Long> {
  Optional<Media> findByTitleAndType(String title, String type);

  Optional<Media> findBySourceAndSourceId(SourceType source, String sourceId);

  boolean existsByTitleAndType(String title, String type);
}
