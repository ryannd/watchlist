package com.ryannd.watchlist.provider;

import com.ryannd.watchlist.media.MediaItem;
import java.util.List;

public interface MediaProvider {
  List<MediaItem> searchByQuery(String query);

  String getProviderName();
}
