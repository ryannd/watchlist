package com.ryannd.watchlist.features.list.model;

import com.ryannd.watchlist.features.list.model.metadata.EntryMetadata;
import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.media.model.MediaType;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "entry")
public class EntryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "media_id", nullable = false)
  private MediaEntity media;

  @Enumerated(EnumType.STRING)
  private MediaType mediaType;

  @Type(JsonType.class)
  @Column(columnDefinition = "jsonb")
  private EntryMetadata metadata;
}
