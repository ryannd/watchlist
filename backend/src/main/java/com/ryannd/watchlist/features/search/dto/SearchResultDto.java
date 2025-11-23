package com.ryannd.watchlist.features.search.dto;

public record SearchResultDto(
    int id,
    String title,
    String overview,
    String backdropPath,
    String posterPath,
    String mediaType,
    String releaseDate) {}
