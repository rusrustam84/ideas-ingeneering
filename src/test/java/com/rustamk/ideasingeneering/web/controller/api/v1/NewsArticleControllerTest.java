package com.rustamk.ideasingeneering.web.controller.api.v1;

import com.rustamk.ideasingeneering.service.mapservice.newsarticleservice.NewsArticleService;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import com.rustamk.ideasingeneering.web.model.RelevanceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//todo implement tests
@WebMvcTest(NewsArticleController.class)
class NewsArticleControllerTest {

  @MockBean
  private NewsArticleService newsArticleService;

  @Autowired
  MockMvc mockMvc;

  NewsArticleDto newsArticleDto;

  @BeforeEach
  void setUp() {
    newsArticleDto = new NewsArticleDto();
    newsArticleDto.setId(1L);
    newsArticleDto.setTitle("Test news article");
    newsArticleDto.setText("Very interesting article!!!");
    newsArticleDto.setRelevance(RelevanceDto.HOT);
    newsArticleDto.setCreationDate(OffsetDateTime.now());
  }

  @AfterEach
  void tearDown() {
    reset(newsArticleService);
  }

  @Test
  void findAll() throws Exception {
    given(newsArticleService.findAll()).willReturn(Set.of(newsArticleDto));

    MvcResult result = mockMvc.perform(get("/api/v1/news/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }

  @Test
  void findById() {
  }

  @Test
  void save() {
  }

  @Test
  void update() {
  }

  @Test
  void deleteById() {
  }
}