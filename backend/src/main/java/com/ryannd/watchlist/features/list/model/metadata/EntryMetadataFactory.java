package com.ryannd.watchlist.features.list.model.metadata;

import com.ryannd.watchlist.features.list.model.EntryStatus;
import com.ryannd.watchlist.features.media.model.MediaType;
import java.time.Instant;
import org.springframework.stereotype.Component;

@Component
public class EntryMetadataFactory {
  public EntryMetadata create(MediaType type) {
    Long now = Instant.now().toEpochMilli();

    return switch (type) {
      case MOVIE -> new MovieEntryMetadata(EntryStatus.PLANNING, now, null, 0);
      case SHOW -> new ShowEntryMetadata(EntryStatus.PLANNING, now, null, null, null, null);
    };
  }
}
