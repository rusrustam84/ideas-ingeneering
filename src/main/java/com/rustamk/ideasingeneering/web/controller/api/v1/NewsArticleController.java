package com.rustamk.ideasingeneering.web.controller.api.v1;

import com.rustamk.ideasingeneering.service.mapservice.newsarticleservice.NewsArticleService;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/news")
@RestController
public class NewsArticleController {

  private final NewsArticleService newsArticleService;

  @GetMapping(
      value = "/articles",
      produces = {"application/json"})
  ResponseEntity<Set<NewsArticleDto>> findAll() {
    Set<NewsArticleDto> articleDtos = newsArticleService.findAll();
    return ResponseEntity.ok(articleDtos);
  }

  @GetMapping(
      value = "article/{id}",
      produces = {"application/json"})
  ResponseEntity<NewsArticleDto> findById(@PathVariable Long id) {
    NewsArticleDto newsArticleDto = newsArticleService.findById(id);
    return ResponseEntity.ok(newsArticleDto);
  }

  @PostMapping(
      value = "article/save",
      produces = "application/json",
      consumes = "application/json")
  ResponseEntity<NewsArticleDto> save(@RequestBody NewsArticleDto newsArticleDto) {
    NewsArticleDto articleDto = newsArticleService.save(newsArticleDto);
    return ResponseEntity.status(HttpStatus.CREATED).body(articleDto);
  }

  @PutMapping(
      value = "/article/{id}/update",
      produces = "application/json",
      consumes = "application/json"
  )
  ResponseEntity<NewsArticleDto> update(@PathVariable Long id, @RequestBody NewsArticleDto newsArticleDto) {
    NewsArticleDto updatedArticle = newsArticleService.update(id, newsArticleDto);
    return ResponseEntity.ok(updatedArticle);
  }

  @DeleteMapping(
      value = "article/delete",
      consumes = "application/json"
  )
  ResponseEntity<String> deleteById(@RequestBody NewsArticleDto newsArticleDto) {
    newsArticleService.delete(newsArticleDto);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
