package com.ryannd.watchlist.features.search.model;

public class SearchResult {
  private int id;
  private String title;
  private String overview;
  private String backdropPath;
  private String posterPath;
  private String mediaType;
  private String releaseDate;

  public SearchResult(
      int id,
      String title,
      String overview,
      String backdropPath,
      String posterPath,
      String mediaType,
      String releaseDate) {
    this.id = id;
    this.title = title;
    this.overview = overview;
    this.backdropPath = backdropPath;
    this.posterPath = posterPath;
    this.mediaType = mediaType;
    this.releaseDate = releaseDate;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }
}
