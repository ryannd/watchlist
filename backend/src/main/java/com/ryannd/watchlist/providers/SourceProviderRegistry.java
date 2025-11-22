package com.ryannd.watchlist.providers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class SourceProviderRegistry {

  private final Map<SourceType, SourceProvider> providers = new HashMap<>();

  public SourceProviderRegistry(List<SourceProvider> providerList) {
    providerList.forEach(p -> providers.put(p.getSourceType(), p));
  }

  public SourceProvider get(SourceType type) {
    return providers.get(type);
  }
}
