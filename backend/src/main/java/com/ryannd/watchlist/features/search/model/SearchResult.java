package com.ryannd.watchlist.features.search.model;

public record SearchResult(
    int id,
    String title,
    String overview,
    String backdropPath,
    String posterPath,
    String mediaType,
    String releaseDate) {}
