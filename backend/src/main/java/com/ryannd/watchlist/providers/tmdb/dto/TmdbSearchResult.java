package com.ryannd.watchlist.providers.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TmdbSearchResult {
  private boolean adult;
  private String backdropPath;
  private int id;
  private String title;
  private String originalLanguage;
  private String overview;
  private String posterPath;
  private String mediaType;
  private List<Integer> genreIds;
  private float popularity;
  private String releaseDate;
  private boolean video;
  private float voteAverage;
  private float voteCount;

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @JsonProperty("title")
  @JsonAlias({"name", "original_title", "original_name"})
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @JsonProperty("original_language")
  public String getOriginalLanguage() {
    return originalLanguage;
  }

  public void setOriginalLanguage(String originalLanguage) {
    this.originalLanguage = originalLanguage;
  }

  public String getOverview() {
    return overview;
  }

  public void setOverview(String overview) {
    this.overview = overview;
  }

  @JsonProperty("poster_path")
  public String getPosterPath() {
    return posterPath;
  }

  public void setPosterPath(String posterPath) {
    this.posterPath = posterPath;
  }

  @JsonProperty("media_type")
  public String getMediaType() {
    return mediaType;
  }

  public void setMediaType(String mediaType) {
    this.mediaType = mediaType;
  }

  @JsonProperty("genre_ids")
  public List<Integer> getGenreIds() {
    return genreIds;
  }

  public void setGenreIds(List<Integer> genreIds) {
    this.genreIds = genreIds;
  }

  public float getPopularity() {
    return popularity;
  }

  public void setPopularity(float popularity) {
    this.popularity = popularity;
  }

  @JsonProperty("release_date")
  @JsonAlias({"first_air_date"})
  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
    this.releaseDate = releaseDate;
  }

  public boolean isVideo() {
    return video;
  }

  public void setVideo(boolean video) {
    this.video = video;
  }

  @JsonProperty("vote_average")
  public float getVoteAverage() {
    return voteAverage;
  }

  public void setVoteAverage(float voteAverage) {
    this.voteAverage = voteAverage;
  }

  @JsonProperty("vote_count")
  public float getVoteCount() {
    return voteCount;
  }

  public void setVoteCount(float voteCount) {
    this.voteCount = voteCount;
  }
}
