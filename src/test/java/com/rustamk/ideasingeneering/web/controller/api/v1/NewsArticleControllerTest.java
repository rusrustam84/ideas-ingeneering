package com.rustamk.ideasingeneering.web.controller.api.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.rustamk.ideasingeneering.service.mapservice.newsarticleservice.NewsArticleService;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import com.rustamk.ideasingeneering.web.model.RelevanceDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class NewsArticleControllerTest {

  @Mock
  private NewsArticleService newsArticleService;


  @InjectMocks
  NewsArticleController newsArticleController;

  private MockMvc mockMvc;
  private ObjectMapper objectMapper;

  private NewsArticleDto newsArticleDto;
  private String json;

  @BeforeEach
  void setUp() throws JsonProcessingException {
    newsArticleDto = new NewsArticleDto();
    newsArticleDto.setId(1L);
    newsArticleDto.setTitle("Test news article");
    newsArticleDto.setText("Very interesting article!!!");
    newsArticleDto.setRelevance(RelevanceDto.HOT);
    newsArticleDto.setCreationDate(OffsetDateTime.now());
    objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    json = objectMapper.writeValueAsString(newsArticleDto);


    mockMvc = MockMvcBuilders.standaloneSetup(newsArticleController).build();
  }

  @AfterEach
  void tearDown() {
    reset(newsArticleService);
  }

  @Test
  void findAll() throws Exception {
    given(newsArticleService.findAll()).willReturn(Set.of(newsArticleDto));

    mockMvc.perform(get("/api/v1/news/articles"))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }

  @Test
  void findById() throws Exception {
    given(newsArticleService.findById(anyLong())).willReturn(newsArticleDto);

    mockMvc.perform(get("/api/v1/news/article/"+newsArticleDto.getId()))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andReturn();
  }

  @Test
  void save() throws Exception {
    given(newsArticleService.save(any(NewsArticleDto.class))).willReturn(newsArticleDto);

    mockMvc.perform(post("/api/v1/news/article/save")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andReturn();

  }

  @Test
  void update() throws Exception {
    given(newsArticleService.update(anyLong(),any(NewsArticleDto.class))).willReturn(newsArticleDto);

    mockMvc.perform(put("/api/v1/news/article/"+newsArticleDto.getId()+"/update")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andReturn();

  }

  @Test
  void deleteById() throws Exception {
    mockMvc.perform(delete("/api/v1/news/article/delete")
        .contentType(MediaType.APPLICATION_JSON)
        .content(json))
        .andExpect(status().isNoContent())
        .andReturn();
  }
}