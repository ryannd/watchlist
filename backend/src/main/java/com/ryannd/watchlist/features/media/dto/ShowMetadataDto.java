package com.ryannd.watchlist.features.media.dto;

import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import java.util.List;

public record ShowMetadataDto(
    String description,
    String backdropPath,
    String posterPath,
    List<String> genres,
    List<Season> seasons,
    String firstAirDate,
    boolean isAiring)
    implements MediaMetadataDto {}
