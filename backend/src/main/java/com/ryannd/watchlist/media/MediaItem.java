package com.ryannd.watchlist.media;

public class MediaItem {
  private String id;
  private String title;
  private String mediaType;
  private String source;
  private String sourceId;

  public MediaItem(String id, String title, String mediaType, String source, String sourceId) {
    this.id = id;
    this.title = title;
    this.mediaType = mediaType;
    this.source = source;
    this.sourceId = sourceId;
  }

  public String getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getMediaType() {
    return mediaType;
  }

  public String getSource() {
    return source;
  }

  public String getSourceId() {
    return sourceId;
  }
}
