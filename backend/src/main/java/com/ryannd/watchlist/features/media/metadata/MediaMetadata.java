package com.ryannd.watchlist.features.media.metadata;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.ryannd.watchlist.features.media.dto.MediaMetadataDto;
import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
  @JsonSubTypes.Type(value = MovieMetadata.class, name = "MOVIE"),
  @JsonSubTypes.Type(value = ShowMetadata.class, name = "SHOW")
})
public abstract class MediaMetadata {
  private String description;
  private String backgroundImage;
  private String posterImage;
  private List<String> genres;

  protected MediaMetadata() {}

  public MediaMetadata(MediaMetadataDto dto) {
    this.description = dto.description();
    this.backgroundImage = dto.backdropPath();
    this.posterImage = dto.posterPath();
    this.genres = dto.genres();
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getBackgroundImage() {
    return backgroundImage;
  }

  public void setBackgroundImage(String backgroundImage) {
    this.backgroundImage = backgroundImage;
  }

  public String getPosterImage() {
    return posterImage;
  }

  public void setPosterImage(String posterImage) {
    this.posterImage = posterImage;
  }

  public List<String> getGenres() {
    return genres;
  }

  public void setGenres(List<String> genres) {
    this.genres = genres;
  }
}
