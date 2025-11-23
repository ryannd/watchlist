package com.ryannd.watchlist.features.search.model;

import java.util.List;

public record SearchResponse(List<SearchResult> results, int currentPage, int totalPages) {}
