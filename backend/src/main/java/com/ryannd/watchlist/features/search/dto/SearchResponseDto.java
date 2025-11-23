package com.ryannd.watchlist.features.search.dto;

import java.util.List;

public record SearchResponseDto(List<SearchResultDto> results, int currentPage, int totalPages) {}
