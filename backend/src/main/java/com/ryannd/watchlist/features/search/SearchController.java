package com.ryannd.watchlist.features.search;

import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.providers.SourceType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
public class SearchController {
  private final SearchService searchService;

  public SearchController(SearchService searchService) {
    this.searchService = searchService;
  }

  @GetMapping("/")
  public SearchResponse getResults(
      @RequestParam String query,
      @RequestParam(defaultValue = "1", required = false) String page,
      @RequestParam(defaultValue = "TMDB") SourceType sourceType) {
    return this.searchService.search(query, page, sourceType);
  }
}
