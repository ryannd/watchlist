package com.ryannd.watchlist.features.search;

import com.ryannd.watchlist.features.search.dto.SearchResponseDto;
import com.ryannd.watchlist.providers.SourceType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
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

  @GetMapping
  public ResponseEntity<SearchResponseDto> search(
      @RequestParam @NotBlank String query,
      @RequestParam(defaultValue = "1") @Min(1) int page,
      @RequestParam(defaultValue = "TMDB") SourceType source) {
    return ResponseEntity.ok(searchService.search(query, page, source));
  }
}
