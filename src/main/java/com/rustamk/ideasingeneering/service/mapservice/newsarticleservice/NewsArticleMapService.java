package com.rustamk.ideasingeneering.service.mapservice.newsarticleservice;

import com.rustamk.ideasingeneering.service.AbstractMapService;
import com.rustamk.ideasingeneering.service.mapservice.util.CSVNewsArticleDataUtil;
import com.rustamk.ideasingeneering.web.mapper.NewsArticleMapper;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"map", "default"})
public class NewsArticleMapService extends AbstractMapService<NewsArticleDto, Long> implements NewsArticleService {

  private final CSVNewsArticleDataUtil csvNewsArticleDataUtil;
  private final NewsArticleMapper newsArticleMapper;

  public NewsArticleMapService(NewsArticleMapper newsArticleMapper, CSVNewsArticleDataUtil csvNewsArticleDataUtil) {
    this.newsArticleMapper = newsArticleMapper;
    this.csvNewsArticleDataUtil = csvNewsArticleDataUtil;
    csvNewsArticleDataUtil.fetchData().forEach(this::save);
  }

  @Override
  public Set<NewsArticleDto> findAll() {
    return super.findAll();
  }

  @Override
  public NewsArticleDto findById(Long aLong) {
    return super.findById(aLong);
  }

  @Override
  public NewsArticleDto save(NewsArticleDto newsArticleDto) {
    return super.save(evaluateRelevance(newsArticleDto));
  }

  @Override
  public void deleteById(Long aLong) {
    super.deleteById(aLong);
  }

  @Override
  public void delete(NewsArticleDto object) {
    super.delete(object);
  }

  @Override
  public NewsArticleDto update(Long id, NewsArticleDto newsArticle) {
    NewsArticleDto savedNewsArticle = mapDataSource.get(id);
    savedNewsArticle.setTitle(newsArticle.getTitle());
    savedNewsArticle.setText(newsArticle.getText());
    savedNewsArticle.setRelevance(newsArticle.getRelevance());
    savedNewsArticle.setCreationDate(newsArticle.getCreationDate());

    return mapDataSource.put(id, savedNewsArticle);
  }
}
