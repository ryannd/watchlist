package com.ryannd.watchlist.providers.tmdb;

import com.ryannd.watchlist.features.media.dto.MediaDto;
import com.ryannd.watchlist.features.media.dto.MovieDto;
import com.ryannd.watchlist.features.media.dto.ShowDto;
import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.SourceType;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbMovieResponse;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResponse;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResult;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbShowResponse;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TmdbProvider implements SourceProvider {
  private final TmdbClient tmdbClient;

  public TmdbProvider(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  @Override
  public SourceType getSourceType() {
    return SourceType.TMDB;
  }

  @Override
  public MediaDto fetchMedia(MediaType type, String id) {
    return switch (type) {
      case MOVIE -> fetchMovie(id);
      case SHOW -> fetchShow(id);
    };
  }

  private MovieDto fetchMovie(String id) {
    TmdbMovieResponse response = tmdbClient.getMovie(id).block();
    return new MovieDto(
        response.getTitle(),
        response.getOverview(),
        response.getPosterPath(),
        response.getBackdropPath(),
        response.getGenres().stream().map(g -> g.getName()).toList(),
        response.getRuntime(),
        response.getReleaseDate());
  }

  private ShowDto fetchShow(String id) {
    TmdbShowResponse response = tmdbClient.getShow(id).block();
    List<Season> seasons =
        response.getSeasons().stream()
            .map(s -> new Season(s.getSeasonNumber(), s.getEpisodeCount()))
            .toList();

    return new ShowDto(
        response.getName(),
        response.getOverview(),
        response.getPosterPath(),
        response.getBackdropPath(),
        response.getGenres().stream().map(g -> g.getName()).toList(),
        seasons,
        response.getFirstAirDate(),
        response.isInProduction());
  }

  @Override
  public SearchResponse searchByQuery(String query, String page) {
    TmdbSearchResponse response = tmdbClient.search(query, page).block();
    List<SearchResult> results = response.getResults().stream().map(this::toSearchResult).toList();

    return new SearchResponse(results, response.getPage(), response.getTotalPages());
  }

  private SearchResult toSearchResult(TmdbSearchResult result) {
    return new SearchResult(
        result.getId(),
        result.getTitle(),
        result.getOverview(),
        result.getBackdropPath(),
        result.getPosterPath(),
        result.getMediaType(),
        result.getReleaseDate());
  }
}
