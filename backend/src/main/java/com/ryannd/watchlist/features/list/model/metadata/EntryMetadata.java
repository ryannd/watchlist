package com.ryannd.watchlist.features.list.model.metadata;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ryannd.watchlist.features.list.model.EntryStatus;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MovieEntryMetadata.class, name = "MOVIE"),
  @JsonSubTypes.Type(value = ShowEntryMetadata.class, name = "SHOW")
})
public abstract class EntryMetadata {
  private EntryStatus status;
  private Long startDate;
  private Long endDate;
  private Integer timesRewatched;

  public EntryMetadata(EntryStatus status, Long startDate, Long endDate, Integer timesRewatched) {
    this.status = status;
    this.startDate = startDate;
    this.endDate = endDate;
    this.timesRewatched = timesRewatched;
  }

  public EntryStatus getStatus() {
    return status;
  }

  public void setStatus(EntryStatus status) {
    this.status = status;
  }

  public Long getStartDate() {
    return startDate;
  }

  public void setStartDate(Long startDate) {
    this.startDate = startDate;
  }

  public Long getEndDate() {
    return endDate;
  }

  public void setEndDate(Long endDate) {
    this.endDate = endDate;
  }

  public Integer getTimesRewatched() {
    return timesRewatched;
  }

  public void setTimesRewatched(Integer timesRewatched) {
    this.timesRewatched = timesRewatched;
  }
}
