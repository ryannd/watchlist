package com.ryannd.watchlist.features.media.metadata;

import java.util.List;

public class ShowMetadata extends MediaMetadata {
  public static class Season {
    private int seasonNumber;
    private int episodeCount;

    public Season(int seasonNumber, int episodeCount) {
      this.seasonNumber = seasonNumber;
      this.episodeCount = episodeCount;
    }

    public int getSeasonNumber() {
      return seasonNumber;
    }

    public void setSeasonNumber(int seasonNumber) {
      this.seasonNumber = seasonNumber;
    }

    public int getEpisodeCount() {
      return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
      this.episodeCount = episodeCount;
    }
  }

  private List<Season> seasons;
  private String firstAirDate;
  private boolean isAiring;

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

  public ShowMetadata(
      String description,
      String backgroundImage,
      String posterImage,
      List<String> genres,
      List<Season> seasons,
      String firstAirDate,
      boolean isAiring) {
    super(description, backgroundImage, posterImage, genres);
    this.seasons = seasons;
    this.firstAirDate = firstAirDate;
    this.isAiring = isAiring;
  }
}
