package com.ryannd.watchlist.features.media;

import com.ryannd.watchlist.features.media.model.MovieResponse;
import com.ryannd.watchlist.features.media.model.ShowResponse;
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
  public ShowResponse getShow(@RequestParam String id) {
    return this.mediaService.getShow(id);
  }

  @GetMapping("/movie")
  public MovieResponse getMovie(@RequestParam String id) {
    return this.mediaService.getMovie(id);
  }
}
