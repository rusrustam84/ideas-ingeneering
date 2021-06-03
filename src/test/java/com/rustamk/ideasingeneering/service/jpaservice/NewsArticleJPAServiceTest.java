package com.rustamk.ideasingeneering.service.jpaservice;

import com.rustamk.ideasingeneering.repository.NewsArticleRepository;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import com.rustamk.ideasingeneering.web.model.RelevanceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class NewsArticleJPAServiceTest {

  @Autowired
  private NewsArticleRepository newsArticleRepository;
  @Autowired
  private NewsArticleJPAService newsArticleJPAService;

  private NewsArticleDto newsArticleDto;

  @BeforeEach
  void setUp() {
    newsArticleDto = new NewsArticleDto();
    newsArticleDto.setId(4L);
    newsArticleDto.setTitle("Test news article");
    newsArticleDto.setText("Very interesting article!!!");
    newsArticleDto.setRelevance(RelevanceDto.HOT);
    newsArticleDto.setCreationDate(OffsetDateTime.now());
  }

  @Test
  void findAll() {
    // given - non

    // when
    Set<NewsArticleDto> articleDtos = newsArticleJPAService.findAll();

    // then
    assertNotNull(articleDtos);
    assertEquals(3, articleDtos.size());
  }

  @Test
  void findById() {
    // given - none

    // when
    NewsArticleDto newsArticleDto = newsArticleJPAService.findById(1L);

    // then
    assertNotNull(newsArticleDto);
    assertEquals(1L, newsArticleDto.getId());

  }

  @Test
  void save() {
    // given - none

    // when
    NewsArticleDto savedNewsArticleDto = newsArticleJPAService.save(newsArticleDto);

    // then
    assertNotNull(savedNewsArticleDto);
  }

  @Test
  void deleteById() {
    // given - none

    // when
    newsArticleJPAService.deleteById(1L);

    // then
    Set<NewsArticleDto> dtos = newsArticleJPAService.findAll();
    assertNotNull(dtos);
  }

  @Test
  void delete() {
    // given - none
    newsArticleJPAService.save(newsArticleDto);

    // when
    newsArticleJPAService.delete(newsArticleDto);

    // then
    Set<NewsArticleDto> dtos = newsArticleJPAService.findAll();
    assertEquals(3, dtos.size());
  }

  @Test
  void update() {
    // given - none

    // when
    NewsArticleDto updatedNewsArticle = newsArticleJPAService.update(1L, newsArticleDto);

    // then
    assertNotNull(updatedNewsArticle);
  }
}