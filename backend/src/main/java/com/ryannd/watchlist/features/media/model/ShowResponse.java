package com.ryannd.watchlist.features.media.model;

import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import java.util.List;

public class ShowResponse {
  private String title;
  private String description;
  private String posterPath;
  private String backgroundPath;
  private List<String> genres;
  private List<Season> seasons;
  private String firstAirDate;
  private boolean isAiring;

  public ShowResponse(
      String title,
      String description,
      String posterPath,
      String backgroundPath,
      List<String> genres,
      List<Season> seasons,
      String firstAirDate,
      boolean isAiring) {
    this.title = title;
    this.description = description;
    this.posterPath = posterPath;
    this.backgroundPath = backgroundPath;
    this.genres = genres;
    this.seasons = seasons;
    this.firstAirDate = firstAirDate;
    this.isAiring = isAiring;
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

  public List<Season> getSeasons() {
    return seasons;
  }

  public void setSeasons(List<Season> seasons) {
    this.seasons = seasons;
  }

  public String getFirstAirDate() {
    return firstAirDate;
  }

  public void setFirstAirDate(String firstAirDate) {
    this.firstAirDate = firstAirDate;
  }

  public boolean isAiring() {
    return isAiring;
  }

  public void setAiring(boolean isAiring) {
    this.isAiring = isAiring;
  }
}
