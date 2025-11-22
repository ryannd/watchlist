package com.ryannd.watchlist.features.media;

import com.ryannd.watchlist.features.media.model.Media;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.ryannd.watchlist.providers.SourceType;
import org.springframework.web.bind.annotation.GetMapping;
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

  @GetMapping("/show")
  public Media getShow(@RequestParam String id, @RequestParam SourceType source) {
    return this.mediaService.getMedia(MediaType.SHOW, source, id);
  }

  @GetMapping("/movie")
  public Media getMovie(@RequestParam String id, @RequestParam SourceType source) {
    return this.mediaService.getMedia(MediaType.MOVIE, source, id);
  }
}
