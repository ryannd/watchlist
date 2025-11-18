package com.ryannd.watchlist.features.list.model.metadata;

import com.ryannd.watchlist.features.list.model.EntryStatus;

public final class ShowEntryMetadata extends EntryMetadata {
  private Integer currentSeason;
  private Integer currentEpisode;

  public ShowEntryMetadata(
      EntryStatus status,
      Long startDate,
      Long endDate,
      Integer timesRewatched,
      Integer currentSeason,
      Integer currentEpisode) {
    super(status, startDate, endDate, timesRewatched);
    this.currentSeason = currentSeason;
    this.currentEpisode = currentEpisode;
  }

  public Integer getCurrentSeason() {
    return currentSeason;
  }

  public void setCurrentSeason(Integer currentSeason) {
    this.currentSeason = currentSeason;
  }

  public Integer getCurrentEpisode() {
    return currentEpisode;
  }

  public void setCurrentEpisode(Integer currentEpisode) {
    this.currentEpisode = currentEpisode;
  }
}
