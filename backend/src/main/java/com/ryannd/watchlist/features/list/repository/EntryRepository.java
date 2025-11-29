package com.ryannd.watchlist.features.list.repository;

import com.ryannd.watchlist.features.list.model.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<EntryEntity, Long> {}
