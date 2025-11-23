package com.ryannd.watchlist.features.media.dto.media;

import com.ryannd.watchlist.features.media.model.MediaType;
import java.util.List;

public record MovieDto(
    String title,
    String description,
    String posterPath,
    String backdropPath,
    List<String> genres,
    Integer runtime,
    String releaseDate)
    implements MediaDto {
  @Override
  public MediaType type() {
    return MediaType.MOVIE;
  }
}
