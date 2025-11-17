package com.ryannd.watchlist.features.media.model;

import java.util.List;

public class MovieResponse {
  private String title;
  private String description;
  private String posterPath;
  private String backgroundPath;
  private List<String> genres;
  private Integer runtime;
  private String releaseDate;

  public MovieResponse(
      String title,
      String description,
      String posterPath,
      String backgroundPath,
      List<String> genres,
      Integer runtime,
      String releaseDate) {
    this.title = title;
    this.description = description;
    this.posterPath = posterPath;
    this.backgroundPath = backgroundPath;
    this.genres = genres;
    this.runtime = runtime;
    this.releaseDate = releaseDate;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getBackgroundPath() {
    return backgroundPath;
  }

  public void setBackgroundPath(String backgroundPath) {
    this.backgroundPath = backgroundPath;
  }

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
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
