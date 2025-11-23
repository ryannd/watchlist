package com.ryannd.watchlist.features.media.model;

import com.ryannd.watchlist.features.list.model.EntryEntity;
import com.ryannd.watchlist.features.media.dto.metadata.MediaMetadataDto;
import com.ryannd.watchlist.features.media.dto.metadata.MovieMetadataDto;
import com.ryannd.watchlist.features.media.dto.metadata.ShowMetadataDto;
import com.ryannd.watchlist.features.media.metadata.MediaMetadata;
import com.ryannd.watchlist.features.media.metadata.MovieMetadata;
import com.ryannd.watchlist.features.media.metadata.ShowMetadata;
import com.ryannd.watchlist.providers.SourceType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.Type;

@Entity
@Table(
    name = "media",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"title", "source"})})
public class MediaEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String title;

  @Enumerated(EnumType.STRING)
  private MediaType type;

  @Enumerated(EnumType.STRING)
  private SourceType source;

  private String sourceId;

  @Type(JsonType.class)
  @Column(columnDefinition = "jsonb")
  private MediaMetadata metadata;

  @OneToMany(mappedBy = "media", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EntryEntity> entries = new ArrayList<>();

  public MediaEntity() {}

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

  public SourceType getSource() {
    return source;
  }

  public void setSource(SourceType source) {
    this.source = source;
  }

  public MediaMetadata getMetadata() {
    return metadata;
  }

  public void setMetadata(MediaMetadataDto dto) {
    this.metadata =
        switch (dto) {
          case MovieMetadataDto m -> new MovieMetadata(m);
          case ShowMetadataDto s -> new ShowMetadata(s);
        };
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public List<EntryEntity> getEntries() {
    return entries;
  }

  public void setEntries(List<EntryEntity> entries) {
    this.entries = entries;
  }

  public String getSourceId() {
    return sourceId;
  }

  public void setSourceId(String sourceId) {
    this.sourceId = sourceId;
  }
}
