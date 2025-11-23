package com.ryannd.watchlist.features.media.metadata;

import com.ryannd.watchlist.features.media.dto.metadata.MovieMetadataDto;

public final class MovieMetadata extends MediaMetadata {
  private Integer runtime;
  private String releaseDate;

  public MovieMetadata() {}

  public MovieMetadata(MovieMetadataDto dto) {
    super(dto);
    this.runtime = dto.runtime();
    this.releaseDate = dto.releaseDate();
  }

  public Integer getRuntime() {
    return runtime;
  }

  public void setRuntime(Integer runtime) {
    this.runtime = runtime;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
