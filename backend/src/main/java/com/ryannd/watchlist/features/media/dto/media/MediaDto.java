package com.ryannd.watchlist.features.media.dto.media;

import com.ryannd.watchlist.features.media.dto.metadata.MediaMetadataDto;
import com.ryannd.watchlist.features.media.dto.metadata.MovieMetadataDto;
import com.ryannd.watchlist.features.media.dto.metadata.ShowMetadataDto;
import com.ryannd.watchlist.features.media.model.MediaType;
import java.util.List;

public sealed interface MediaDto permits MovieDto, ShowDto {
  String title();

  String description();

  String posterPath();

  String backdropPath();

  List<String> genres();

  MediaType type();

  default MediaMetadataDto toMetadata() {
    return switch (this) {
      case MovieDto m ->
          new MovieMetadataDto(
              m.description(),
              m.backdropPath(),
              m.posterPath(),
              m.genres(),
              m.runtime(),
              m.releaseDate());
      case ShowDto s ->
          new ShowMetadataDto(
              s.description(),
              s.backdropPath(),
              s.posterPath(),
              s.genres(),
              s.seasons(),
              s.firstAirDate(),
              s.isAiring());
    };
  }
}
