package com.rustamk.ideasingeneering.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.OffsetDateTime;

@EqualsAndHashCode
public class NewsArticleDto extends BaseDto {

  public NewsArticleDto() {
    super(null);
  }

  public NewsArticleDto(Long id, String title, String text, RelevanceDto relevance, OffsetDateTime creationDate) {
    super(id);
    this.title = title;
    this.text = text;
    this.relevance = relevance;
    this.creationDate = creationDate;
  }

  @JsonProperty("title")
  private String title;

  @JsonProperty("text")
  private String text;

  @JsonProperty("relevance")
  RelevanceDto relevance;

  @JsonProperty("creationDate")
  OffsetDateTime creationDate;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  public RelevanceDto getRelevance() {
    return relevance;
  }

  public void setRelevance(RelevanceDto relevance) {
    this.relevance = relevance;
  }

  public OffsetDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(OffsetDateTime creationDate) {
    this.creationDate = creationDate;
  }
}
