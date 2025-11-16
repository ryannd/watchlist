package com.ryannd.watchlist.features.media.metadata;

import java.util.List;

public class MovieMetadata extends MediaMetadata {
  private Integer runtime;
  private String releaseDate;

  public MovieMetadata(
      String description,
      String backgroundImage,
      String posterImage,
      List<String> genres,
      Integer runtime,
      String releaseDate) {
    super(description, backgroundImage, posterImage, genres);
    this.runtime = runtime;
    this.releaseDate = releaseDate;
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
