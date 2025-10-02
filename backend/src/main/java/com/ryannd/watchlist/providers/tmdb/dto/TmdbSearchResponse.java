package com.ryannd.watchlist.providers.tmdb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class TmdbSearchResponse {
  private int page;
  private List<TmdbSearchResult> results;
  private int totalPages;
  private int totalResults;

  public int getPage() {
    return page;
  }

  public void setPage(int page) {
    this.page = page;
  }

  public List<TmdbSearchResult> getResults() {
    return results;
  }

  public void setResults(List<TmdbSearchResult> results) {
    this.results = results;
  }

  @JsonProperty("total_pages")
  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  @JsonProperty("total_results")
  public int getTotalResults() {
    return totalResults;
  }

  public void setTotalResults(int totalResults) {
    this.totalResults = totalResults;
  }
}
