package com.ryannd.watchlist.media.provider;

import com.ryannd.watchlist.media.MediaItem;
import java.util.List;

public interface MediaProvider {
  List<MediaItem> searchByQuery(String query);

  String getProviderName();
}
