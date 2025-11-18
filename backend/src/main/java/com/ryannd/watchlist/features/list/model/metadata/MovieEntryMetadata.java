package com.ryannd.watchlist.features.list.model.metadata;

import com.ryannd.watchlist.features.list.model.EntryStatus;

public final class MovieEntryMetadata extends EntryMetadata {

  public MovieEntryMetadata(
      EntryStatus status, Long startDate, Long endDate, Integer timesRewatched) {
    super(status, startDate, endDate, timesRewatched);
  }
}
