package com.ryannd.watchlist.providers.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TmdbSeason {
  private String airDate;
  private Integer episodeCount;
  private Integer id;
  private String name;
  private String overview;
  private String posterPath;
  private Integer seasonNumber;

  @JsonProperty("air_date")
  public String getAirDate() {
    return airDate;
  }

  public void setAirDate(String airDate) {
    this.airDate = airDate;
  }

  @JsonProperty("episode_count")
  public Integer getEpisodeCount() {
    return episodeCount;
  }

  public void setEpisodeCount(Integer episodeCount) {
    this.episodeCount = episodeCount;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  @JsonProperty("season_number")
  public Integer getSeasonNumber() {
    return seasonNumber;
  }

  public void setSeasonNumber(Integer seasonNumber) {
    this.seasonNumber = seasonNumber;
  }
}
