package com.rustamk.ideasingeneering.service.jpaservice;

import com.rustamk.ideasingeneering.domain.NewsArticle;
import com.rustamk.ideasingeneering.repository.NewsArticleRepository;
import com.rustamk.ideasingeneering.service.AbstractMapService;
import com.rustamk.ideasingeneering.service.mapservice.newsarticleservice.NewsArticleService;
import com.rustamk.ideasingeneering.service.mapservice.util.CSVNewsArticleDataUtil;
import com.rustamk.ideasingeneering.web.mapper.NewsArticleMapper;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Profile("springdatajpa")
public class NewsArticleJPAService extends AbstractMapService<NewsArticleDto, Long> implements NewsArticleService {

  private final NewsArticleRepository newsArticleRepository;
  private final CSVNewsArticleDataUtil csvNewsArticleDataUtil;
  private final NewsArticleMapper newsArticleMapper;

  public NewsArticleJPAService(NewsArticleRepository newsArticleRepository, NewsArticleMapper newsArticleMapper, CSVNewsArticleDataUtil csvNewsArticleDataUtil) {
    this.newsArticleRepository = newsArticleRepository;
    this.newsArticleMapper = newsArticleMapper;
    this.csvNewsArticleDataUtil = csvNewsArticleDataUtil;
    this.newsArticleRepository.saveAll(csvNewsArticleDataUtil.fetchData()
        .stream()
        .map(this::evaluateRelevance)
        .map(newsArticleMapper::dtoToEntity)
        .collect(Collectors.toList()));
  }

  @Override
  public Set<NewsArticleDto> findAll() {
    Set<NewsArticleDto> newsArticles = new LinkedHashSet();
    newsArticleRepository.findAll().forEach(newArticle -> newsArticles.add(newsArticleMapper.entityToDto(newArticle)));
    return newsArticles;
  }

  @Override
  public NewsArticleDto findById(Long id) {
    return newsArticleMapper.entityToDto(newsArticleRepository.findById(id)
        .orElseGet(null));
  }

  @Override
  public NewsArticleDto save(NewsArticleDto newsArticle) {
    return newsArticleMapper.entityToDto(newsArticleRepository.save(newsArticleMapper.dtoToEntity(evaluateRelevance(newsArticle))));
  }

  @Override
  public void deleteById(Long id) {
    newsArticleRepository.deleteById(id);
  }

  @Override
  public void delete(NewsArticleDto newsArticle) {
    newsArticleRepository.delete(newsArticleMapper.dtoToEntity(newsArticle));
  }

  @Override
  public NewsArticleDto update(Long id, NewsArticleDto newsArticleDto) {
    NewsArticle newsArticle = newsArticleMapper.dtoToEntity(newsArticleDto);
    if (newsArticleRepository.existsById(id)) {
      NewsArticle savedNewsArticle = newsArticleRepository.findById(id).get();
      savedNewsArticle.setTitle(newsArticle.getTitle());
      savedNewsArticle.setText(newsArticle.getText());
      savedNewsArticle.setRelevance(newsArticle.getRelevance());
      savedNewsArticle.setCreationDate(newsArticle.getCreationDate());
      return newsArticleMapper.entityToDto(newsArticleRepository.save(savedNewsArticle));
    }
    return null;
  }
}
