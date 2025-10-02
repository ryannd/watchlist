package com.ryannd.watchlist.providers;

import java.util.List;

public interface SourceProvider {
  <T> List<T> searchByQuery(String query);
}
