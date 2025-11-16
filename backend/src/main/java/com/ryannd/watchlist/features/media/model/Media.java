package com.ryannd.watchlist.features.media.model;

import com.ryannd.watchlist.features.media.metadata.MediaMetadata;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import org.hibernate.annotations.Type;

@Entity
@Table(
    name = "media",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "source"})})
public class Media {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Enumerated(EnumType.STRING)
  private MediaType type;

  @Enumerated(EnumType.STRING)
  private MediaSource source;

  @Type(JsonType.class)
  @Column(columnDefinition = "jsonb")
  private MediaMetadata metadata;

  public Media(Long id, String title, MediaType type, MediaSource source, MediaMetadata metadata) {
    this.id = id;
    this.title = title;
    this.type = type;
    this.source = source;
    this.metadata = metadata;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public MediaType getType() {
    return type;
  }

  public void setType(MediaType type) {
    this.type = type;
  }

  public MediaSource getSource() {
    return source;
  }

  public void setSource(MediaSource source) {
    this.source = source;
  }

  public MediaMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(MediaMetadata metadata) {
    this.metadata = metadata;
  }
}
