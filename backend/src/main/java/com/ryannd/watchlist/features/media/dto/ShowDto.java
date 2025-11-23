package com.ryannd.watchlist.features.media.dto;

import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import com.ryannd.watchlist.features.media.model.MediaType;
import java.util.List;

public record ShowDto(
    String title,
    String description,
    String posterPath,
    String backdropPath,
    List<String> genres,
    List<Season> seasons,
    String firstAirDate,
    boolean isAiring)
    implements MediaDto {
  @Override
  public MediaType type() {
    return MediaType.SHOW;
  }
}
