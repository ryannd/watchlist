package com.ryannd.watchlist.features.list.model.dto;

import com.ryannd.watchlist.features.list.model.metadata.EntryMetadata;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.providers.SourceType;

public record EntryDto(
    MediaType mediaType, SourceType source, String sourceId, EntryMetadata metadata) {}
