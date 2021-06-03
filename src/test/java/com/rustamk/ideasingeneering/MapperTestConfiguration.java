package com.rustamk.ideasingeneering;

import com.rustamk.ideasingeneering.web.mapper.NewsArticleMapper;
import com.rustamk.ideasingeneering.web.mapper.NewsArticleMapperImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MapperTestConfiguration {
  @Bean
  public NewsArticleMapper newsArticleMapper() {
    return new NewsArticleMapperImpl();
  }

  @Bean
  NewsArticleMapperImpl newsArticleMapperImpl() {
    return new NewsArticleMapperImpl();
  }
}
