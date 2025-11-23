package com.ryannd.watchlist.features.media;

import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.providers.SourceType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/media")
public class MediaController {
  private final MediaService mediaService;

  public MediaController(MediaService mediaService) {
    this.mediaService = mediaService;
  }

  @GetMapping("/{type}")
  public ResponseEntity<MediaEntity> getMedia(
      @PathVariable MediaType type,
      @RequestParam String id,
      @RequestParam(defaultValue = "TMDB") SourceType source) {
    MediaEntity media = mediaService.getOrCreateMedia(type, source, id);
    return ResponseEntity.ok(media);
  }
}
