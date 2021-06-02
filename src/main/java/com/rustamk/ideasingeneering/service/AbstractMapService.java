package com.rustamk.ideasingeneering.service;

import com.rustamk.ideasingeneering.web.model.BaseDto;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import com.rustamk.ideasingeneering.web.model.RelevanceDto;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractMapService<T extends BaseDto, ID extends Long> {
  protected Map<Long, T> mapDataSource = new LinkedHashMap<>();

  public Set<T> findAll() {
    return new LinkedHashSet<>(mapDataSource.values());
  }

  public T findById(ID id) {
    return mapDataSource.get(id);
  }

  public T save(T object) {
    if (object != null) {
      if (object.getId() == null) {
        object.setId(getNextId());
      }
      mapDataSource.put(object.getId(), object);
    } else {
      throw new RuntimeException("Object cannot be null");
    }
    return object;
  }

  public void deleteById(ID id) {
    mapDataSource.remove(id);
  }

  public void delete(final T object) {
    mapDataSource.entrySet().removeIf(entry -> entry.getValue().getId().equals(object.getId()));
  }

  public NewsArticleDto evaluateRelevance(NewsArticleDto newsArticle) {
    String text = newsArticle.getText();
    int exclamationMarkCount = findCharCount("!", text);
    int fullStopCount = findCharCount("\\.", text);
    int commasCount = findCharCount(",", text);
    if (exclamationMarkCount > fullStopCount && newsArticle.getCreationDate().isAfter(OffsetDateTime.now().minusMinutes(1))) {
      newsArticle.setRelevance(RelevanceDto.HOT);
    } else if (commasCount > fullStopCount && newsArticle.getCreationDate().isAfter(OffsetDateTime.now().minusMinutes(5))) {
      newsArticle.setRelevance(RelevanceDto.BORING);
    } else {
      newsArticle.setRelevance(RelevanceDto.STANDARD);
    }
    return newsArticle;
  }

  private int findCharCount(String regex, String text) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(text);
    int count = 0;
    while (matcher.find()) {
      count++;
    }
    return count;
  }

  private Long getNextId() {
    Long nextId;
    try {
      nextId = Collections.max(mapDataSource.keySet()) + 1;
    } catch (NoSuchElementException e) {
      nextId = 1L;
    }
    return nextId;
  }

}
