package com.ryannd.watchlist.features.list;

import com.ryannd.watchlist.features.list.model.EntryEntity;
import com.ryannd.watchlist.features.list.model.dto.EntryDto;
import com.ryannd.watchlist.features.list.repository.EntryRepository;
import com.ryannd.watchlist.features.media.MediaService;
import com.ryannd.watchlist.features.media.model.MediaEntity;
import com.ryannd.watchlist.features.user.UserService;
import com.ryannd.watchlist.features.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ListService {
  public final UserService userService;
  public final MediaService mediaService;
  public final EntryRepository entryRepository;

  @Autowired
  public ListService(
      UserService userService, MediaService mediaService, EntryRepository entryRepository) {
    this.userService = userService;
    this.mediaService = mediaService;
    this.entryRepository = entryRepository;
  }

  public EntryEntity addToList(EntryDto entryDto) {
    MediaEntity media =
        mediaService.getOrCreateMedia(entryDto.mediaType(), entryDto.source(), entryDto.sourceId());
    EntryEntity entry = createEntry(entryDto, media);
    UserEntity user = this.userService.getAuthenticatedUser();

    entry.setUser(user);
    user.getWatchList().add(entry);

    return entryRepository.save(entry);
  }

  public EntryEntity createEntry(EntryDto entryDto, MediaEntity media) {
    EntryEntity entry = new EntryEntity();
    entry.setMedia(media);
    entry.setMediaType(entryDto.mediaType());
    entry.setMetadata(entryDto.metadata());
    return entry;
  }
}
