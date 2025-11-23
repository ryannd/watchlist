package com.ryannd.watchlist.features.media.metadata;

import com.ryannd.watchlist.features.media.dto.metadata.ShowMetadataDto;
import java.util.List;

public class ShowMetadata extends MediaMetadata {
  public static class Season {
    private int seasonNumber;
    private int episodeCount;

    public Season() {}

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

  public ShowMetadata() {}

  public ShowMetadata(ShowMetadataDto dto) {
    super(dto);
    this.seasons = dto.seasons();
    this.firstAirDate = dto.firstAirDate();
    this.isAiring = dto.isAiring();
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
