package com.ryannd.watchlist.features.search.model;

import java.util.List;

public class SearchResponse {
  private List<SearchResult> results;
  private int currentPage;
  private int totalPages;

  public SearchResponse(List<SearchResult> results, int currentPage, int totalPages) {
    this.results = results;
    this.currentPage = currentPage;
    this.totalPages = totalPages;
  }

  public List<SearchResult> getResults() {
    return results;
  }

  public void setResults(List<SearchResult> results) {
    this.results = results;
  }

  public int getCurrentPage() {
    return currentPage;
  }

  public void setCurrentPage(int currentPage) {
    this.currentPage = currentPage;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }
}
