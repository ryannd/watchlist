package com.ryannd.watchlist.providers.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TmdbShowResponse {
  private boolean adult;
  private String backdropPath;
  private String firstAirDate;
  private List<TmdbGenre> genres;
  private Integer id;
  private boolean inProduction;
  private String lastAirDate;
  private String name;
  private Integer numberOfSeasons;
  private String originalLanguage;
  private String overview;
  private String posterPath;
  private List<TmdbSeason> seasons;

  public boolean isAdult() {
    return adult;
  }

  public void setAdult(boolean adult) {
    this.adult = adult;
  }

  @JsonProperty("backdrop_path")
  public String getBackdropPath() {
    return backdropPath;
  }

  public void setBackdropPath(String backdropPath) {
    this.backdropPath = backdropPath;
  }

  @JsonProperty("first_air_date")
  public String getFirstAirDate() {
    return firstAirDate;
  }

  public void setFirstAirDate(String firstAirDate) {
    this.firstAirDate = firstAirDate;
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

  @JsonProperty("is_in_production")
  public boolean isInProduction() {
    return inProduction;
  }

  public void setInProduction(boolean inProduction) {
    this.inProduction = inProduction;
  }

  @JsonProperty("last_air_date")
  public String getLastAirDate() {
    return lastAirDate;
  }

  public void setLastAirDate(String lastAirDate) {
    this.lastAirDate = lastAirDate;
  }

  @JsonProperty("name")
  @JsonAlias({"original_name"})
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @JsonProperty("number_of_seasons")
  public Integer getNumberOfSeasons() {
    return numberOfSeasons;
  }

  public void setNumberOfSeasons(Integer numberOfSeasons) {
    this.numberOfSeasons = numberOfSeasons;
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

  public List<TmdbSeason> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<TmdbSeason> seasons) {
    this.seasons = seasons;
  }
}
