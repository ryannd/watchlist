package com.ryannd.watchlist.features.user.model;

import com.ryannd.watchlist.features.list.model.EntryEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
    name = "users",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"firebaseUid"})})
public class UserEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String firebaseUid;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<EntryEntity> watchList = new ArrayList<>();

  public UserEntity() {}

  public UserEntity(Long id, String firebaseUid) {
    this.id = id;
    this.firebaseUid = firebaseUid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirebaseUid() {
    return firebaseUid;
  }

  public void setFirebaseUid(String firebaseUid) {
    this.firebaseUid = firebaseUid;
  }

  public List<EntryEntity> getWatchList() {
    return watchList;
  }

  public void setWatchList(List<EntryEntity> watchList) {
    this.watchList = watchList;
  }
}
