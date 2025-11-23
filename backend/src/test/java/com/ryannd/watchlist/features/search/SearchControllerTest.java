package com.ryannd.watchlist.features.search;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ryannd.watchlist.features.search.dto.SearchResponseDto;
import com.ryannd.watchlist.features.search.dto.SearchResultDto;
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
class SearchControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockitoBean private SearchService searchService;

  @Test
  void search_returnsResults() throws Exception {
    var results =
        List.of(
            new SearchResultDto(
                123,
                "Inception",
                "A mind-bending thriller",
                "backdrop",
                "poster",
                "movie",
                "2010-07-16"));
    var response = new SearchResponseDto(results, 1, 1);

    when(searchService.search("Inception", 1, SourceType.TMDB)).thenReturn(response);

    mockMvc
        .perform(get("/api/search").param("query", "Inception").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.results[0].id").value(123))
        .andExpect(jsonPath("$.results[0].title").value("Inception"))
        .andExpect(jsonPath("$.currentPage").value(1))
        .andExpect(jsonPath("$.totalPages").value(1));
  }

  @Test
  void search_handlesPagination() throws Exception {
    var resultsPageTwo =
        List.of(
            new SearchResultDto(
                456,
                "Interstellar",
                "Space exploration",
                "backdrop2",
                "poster2",
                "movie",
                "2014-11-07"));
    var response = new SearchResponseDto(resultsPageTwo, 2, 5);

    when(searchService.search("space", 2, SourceType.TMDB)).thenReturn(response);

    mockMvc
        .perform(
            get("/api/search")
                .param("query", "space")
                .param("page", "2")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.currentPage").value(2))
        .andExpect(jsonPath("$.totalPages").value(5))
        .andExpect(jsonPath("$.results[0].id").value(456));
  }

  @Test
  void search_validatesQuery() throws Exception {
    mockMvc.perform(get("/api/search").param("query", "")).andExpect(status().isBadRequest());
  }
}
