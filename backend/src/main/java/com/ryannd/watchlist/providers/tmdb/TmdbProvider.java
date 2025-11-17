package com.ryannd.watchlist.providers.tmdb;

import com.ryannd.watchlist.features.media.metadata.ShowMetadata.Season;
import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
import com.ryannd.watchlist.features.search.model.SearchResponse;
import com.ryannd.watchlist.features.search.model.SearchResult;
import com.ryannd.watchlist.providers.SourceProvider;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbMovieResponse;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResponse;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbSearchResult;
import com.ryannd.watchlist.providers.tmdb.dto.TmdbShowResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class TmdbProvider implements SourceProvider {
  private final TmdbClient tmdbClient;

  public TmdbProvider(TmdbClient tmdbClient) {
    this.tmdbClient = tmdbClient;
  }

  @Override
  public SearchResponse searchByQuery(String query, String page) {
    TmdbSearchResponse tmdbResponse = tmdbClient.search(query, page).block();
    List<SearchResult> results =
        tmdbResponse.getResults().stream()
            .filter(r -> !"person".equalsIgnoreCase(r.getMediaType()))
            .map(this::mapToSearchResult)
            .collect(Collectors.toList());

    SearchResponse response =
        new SearchResponse(results, tmdbResponse.getPage(), tmdbResponse.getTotalPages());
    return response;
  }

  private SearchResult mapToSearchResult(TmdbSearchResult result) {
    return new SearchResult(
        result.getId(),
        result.getTitle(),
        result.getOverview(),
        result.getBackdropPath(),
        result.getPosterPath(),
        result.getMediaType(),
        result.getReleaseDate());
  }

  @Override
  public MovieResponse getMovie(String id) {
    TmdbMovieResponse response = tmdbClient.getMovie(id).block();
    List<String> genres =
        response.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList());
    return new MovieResponse(
        response.getTitle(),
        response.getOverview(),
        response.getPosterPath(),
        response.getBackdropPath(),
        genres,
        response.getRuntime(),
        response.getReleaseDate());
  }

  @Override
  public ShowResponse getShow(String id) {
    TmdbShowResponse response = tmdbClient.getShow(id).block();
    List<String> genres =
        response.getGenres().stream().map(genre -> genre.getName()).collect(Collectors.toList());
    List<Season> seasons =
        response.getSeasons().stream()
            .map(season -> new Season(season.getSeasonNumber(), season.getEpisodeCount()))
            .collect(Collectors.toList());
    return new ShowResponse(
        response.getName(),
        response.getOverview(),
        response.getPosterPath(),
        response.getBackdropPath(),
        genres,
        seasons,
        response.getFirstAirDate(),
        response.isInProduction());
  }
}
