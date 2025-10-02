package com.ryannd.watchlist.features.search;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ryannd.watchlist.features.search.model.SearchResult;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(SearchController.class)
public class SearchControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private SearchService searchService;

  @Test
  void getSearch_ReturnsSearchResults() throws Exception {
    List<SearchResult> results =
        List.of(
            new SearchResult(123, "Inception", "overview", "backdrop", "poster", "movie", "2000"));
    when(searchService.search("Inception")).thenReturn(results);

    mockMvc
        .perform(get("/api/search/").param("query", "Inception"))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].id").value(123))
        .andExpect(jsonPath("$[0].title").value("Inception"));
  }
}
