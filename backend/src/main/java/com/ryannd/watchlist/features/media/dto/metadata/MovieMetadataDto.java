package com.ryannd.watchlist.features.media.dto.metadata;

import java.util.List;

public record MovieMetadataDto(
    String description,
    String backdropPath,
    String posterPath,
    List<String> genres,
    Integer runtime,
    String releaseDate)
    implements MediaMetadataDto {}
