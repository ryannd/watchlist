package com.ryannd.watchlist.features.media;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.providers.SourceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(
    controllers = MediaController.class,
    excludeFilters =
        @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = {
              com.ryannd.watchlist.config.SecurityConfig.class,
              com.ryannd.watchlist.features.auth.TokenAuthenticationFilter.class
            }))
@AutoConfigureMockMvc(addFilters = false)
class MediaControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockitoBean private MediaService mediaService;

  @Test
  void getMedia_movie_returnsMovie() throws Exception {
    MediaEntity movie = new MediaEntity();
    movie.setId(1L);
    movie.setTitle("Inception");
    movie.setType(MediaType.MOVIE);
    movie.setSource(SourceType.TMDB);

    when(mediaService.getOrCreateMedia(MediaType.MOVIE, SourceType.TMDB, "123")).thenReturn(movie);

    mockMvc
        .perform(get("/api/media/MOVIE").param("id", "123"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Inception"))
        .andExpect(jsonPath("$.type").value("MOVIE"));
  }

  @Test
  void getMedia_show_returnsShow() throws Exception {
    MediaEntity show = new MediaEntity();
    show.setId(2L);
    show.setTitle("Breaking Bad");
    show.setType(MediaType.SHOW);
    show.setSource(SourceType.TMDB);

    when(mediaService.getOrCreateMedia(MediaType.SHOW, SourceType.TMDB, "456")).thenReturn(show);

    mockMvc
        .perform(get("/api/media/SHOW").param("id", "456"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Breaking Bad"))
        .andExpect(jsonPath("$.type").value("SHOW"));
  }
}
