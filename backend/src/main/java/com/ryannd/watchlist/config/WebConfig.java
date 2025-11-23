package com.ryannd.watchlist.config;

import com.ryannd.watchlist.features.media.model.MediaType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @SuppressWarnings("null")
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**");
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(
        new Converter<String, MediaType>() {
          @Override
          public MediaType convert(String source) {
            return MediaType.valueOf(source.toUpperCase());
          }
        });
  }
}
