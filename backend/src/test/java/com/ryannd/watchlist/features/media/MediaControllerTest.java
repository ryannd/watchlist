package com.ryannd.watchlist.features.media;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MediaController.class)
public class MediaControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private MediaService mediaService;

  @Test
  void getMovie_returnsMovieResponse() throws Exception {
    MovieResponse movie =
        new MovieResponse(
            "Inception",
            "A mind-bending thriller",
            "/poster.jpg",
            "/backdrop.jpg",
            List.of("Sci-Fi", "Thriller"),
            148,
            "2010-07-16");

    when(mediaService.getMovie("123")).thenReturn(movie);

    mockMvc
        .perform(get("/api/media/movie?id={id}", "123").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Inception"))
        .andExpect(jsonPath("$.runtime").value(148))
        .andExpect(jsonPath("$.genres[0]").value("Sci-Fi"))
        .andExpect(jsonPath("$.releaseDate").value("2010-07-16"));
  }

  @Test
  void getShow_returnsShowResponse() throws Exception {
    ShowResponse show =
        new ShowResponse(
            "Example Show",
            "Show description",
            "/poster2.jpg",
            "/backdrop2.jpg",
            List.of("Drama"),
            List.of(new Season(1, 10)),
            "2024-01-01",
            true);

    when(mediaService.getShow("456")).thenReturn(show);

    mockMvc
        .perform(get("/api/media/show?id={id}", "456").accept(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Example Show"))
        .andExpect(jsonPath("$.seasons[0].seasonNumber").value(1))
        .andExpect(jsonPath("$.seasons[0].episodeCount").value(10))
        .andExpect(jsonPath("$.airing").value(true));
  }
}
