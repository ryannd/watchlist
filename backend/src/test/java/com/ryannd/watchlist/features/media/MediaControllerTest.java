package com.ryannd.watchlist.features.media;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ryannd.watchlist.features.media.metadata.MovieMetadata;
import com.ryannd.watchlist.features.media.metadata.ShowMetadata;
import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import com.ryannd.watchlist.features.media.model.Media;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.providers.SourceType;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MediaController.class)
class MediaControllerTest {
  @Autowired private MockMvc mockMvc;

  @MockitoBean private MediaService mediaService;

  @Test
  void getMovie_returnsMovieResponse() throws Exception {
    MovieMetadata metadata =
        new MovieMetadata(
            "A mind-bending thriller",
            "/backdrop.jpg",
            "/poster.jpg",
            List.of("Sci-Fi", "Thriller"),
            148,
            "2010-07-16");

    Media movie = new Media(1L, "Inception", MediaType.MOVIE, SourceType.TMDB, metadata);

    when(mediaService.getMedia(MediaType.MOVIE, SourceType.TMDB, "123")).thenReturn(movie);

    mockMvc
        .perform(
            get("/api/media/movie")
                .param("id", "123")
                .param("source", "TMDB")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Inception"))
        .andExpect(jsonPath("$.type").value("MOVIE"))
        .andExpect(jsonPath("$.metadata.runtime").value(148))
        .andExpect(jsonPath("$.metadata.genres[0]").value("Sci-Fi"))
        .andExpect(jsonPath("$.metadata.releaseDate").value("2010-07-16"));
  }

  @Test
  void getShow_returnsShowResponse() throws Exception {
    ShowMetadata metadata =
        new ShowMetadata(
            "Show description",
            "/backdrop2.jpg",
            "/poster2.jpg",
            List.of("Drama"),
            List.of(new Season(1, 10)),
            "2024-01-01",
            true);

    Media show = new Media(2L, "Example Show", MediaType.SHOW, SourceType.TMDB, metadata);

    when(mediaService.getMedia(MediaType.SHOW, SourceType.TMDB, "456")).thenReturn(show);

    mockMvc
        .perform(
            get("/api/media/show")
                .param("id", "456")
                .param("source", "TMDB")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Example Show"))
        .andExpect(jsonPath("$.type").value("SHOW"))
        .andExpect(jsonPath("$.metadata.seasons[0].seasonNumber").value(1))
        .andExpect(jsonPath("$.metadata.seasons[0].episodeCount").value(10))
        .andExpect(jsonPath("$.metadata.airing").value(true));
  }
}
