package com.ryannd.watchlist.providers.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TmdbMovieResponse {
  private boolean adult;
  private String backdropPath;
  private List<TmdbGenre> genres;
  private Integer id;
  private String title;
  private String overview;
  private String posterPath;
  private String releaseDate;
  private Integer runtime;
  private String originalLanguage;

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  @JsonProperty("backdrop_path")
  public String getBackdropPath() {
    if (backdropPath != null) {
      return "https://image.tmdb.org/t/p/w500" + backdropPath;
    } else {
      return "";
    }
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  public List<TmdbGenre> getGenres() {
    return genres;
  }

  public void setGenres(List<TmdbGenre> genres) {
    this.genres = genres;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @JsonProperty("title")
  @JsonAlias({"original_title"})
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

  @JsonProperty("poster_path")
  public String getPosterPath() {
    if (posterPath != null) {
      return "https://image.tmdb.org/t/p/w500" + posterPath;
    } else {
      return "";
    }
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  @JsonProperty("release_date")
  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Integer getRuntime() {
    return runtime;
  }

  public void setRuntime(Integer runtime) {
    this.runtime = runtime;
  }

  @JsonProperty("original_language")
  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }
}
