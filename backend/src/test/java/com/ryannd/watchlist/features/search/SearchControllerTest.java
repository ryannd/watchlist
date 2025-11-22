package com.ryannd.watchlist.features.search;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
    controllers = SearchController.class,
    excludeFilters =
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = {
              com.ryannd.watchlist.config.SecurityConfig.class,
              com.ryannd.watchlist.features.auth.TokenAuthenticationFilter.class
            }))
@AutoConfigureMockMvc(addFilters = false)
public class SearchControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private SearchService searchService;

  @Test
  void getSearch_ReturnsSearchResults() throws Exception {
    List<SearchResult> results =
        List.of(
            new SearchResult(123, "Inception", "overview", "backdrop", "poster", "movie", "2000"));

    SearchResponse response = new SearchResponse(results, 1, 1);

    when(searchService.search("Inception", "1", SourceType.TMDB)).thenReturn(response);

    mockMvc
        .perform(get("/api/search/").param("query", "Inception"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.results[0].id").value(123))
        .andExpect(jsonPath("$.results[0].title").value("Inception"));
  }

  @Test
  void getSearch_returnsCorrectPage() throws Exception {
    List<SearchResult> resultsPageOne =
        List.of(
            new SearchResult(123, "Inception", "overview", "backdrop", "poster", "movie", "2000"));
    List<SearchResult> resultsPageTwo =
        List.of(new SearchResult(456, "Page2", "overview", "backdrop", "poster", "movie", "2000"));

    SearchResponse resPageOne = new SearchResponse(resultsPageOne, 1, 2);
    SearchResponse resPageTwo = new SearchResponse(resultsPageTwo, 2, 2);

    when(searchService.search("Inception", "1", SourceType.TMDB)).thenReturn(resPageOne);
    when(searchService.search("Inception", "2", SourceType.TMDB)).thenReturn(resPageTwo);

    mockMvc
        .perform(get("/api/search/").param("query", "Inception").param("page", "2"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.results[0].id").value(456))
        .andExpect(jsonPath("$.results[0].title").value("Page2"))
        .andExpect(jsonPath("$.currentPage").value(2));
  }
}
