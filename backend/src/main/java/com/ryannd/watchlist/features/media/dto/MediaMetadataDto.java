package com.ryannd.watchlist.features.media.dto;

import java.util.List;

public sealed interface MediaMetadataDto permits MovieMetadataDto, ShowMetadataDto {
  String description();

  String backdropPath();

  String posterPath();

  List<String> genres();
}
