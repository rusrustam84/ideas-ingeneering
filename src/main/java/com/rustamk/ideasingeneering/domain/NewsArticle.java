package com.rustamk.ideasingeneering.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@Entity
@Table(name = "newsarticle")
public class NewsArticle extends BaseEntity {

  @NonNull
  @Column(name = "title")
  String title;

  @Column(name = "text")
  String text;

  @Enumerated(EnumType.STRING)
  Relevance relevance;

  @NonNull
  @Column(name = "creationDate")
  Timestamp creationDate;

  public NewsArticle(Long id, @NonNull String title, String text, Relevance relevance, @NonNull Timestamp creationDate) {
    super(id);
    this.title = title;
    this.text = text;
    this.relevance = relevance;
    this.creationDate = creationDate;
  }

  public NewsArticle() {
    super(null);
  }

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

  public Relevance getRelevance() {
    return relevance;
  }

  public void setRelevance(Relevance relevance) {
    this.relevance = relevance;
  }

  public Timestamp getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Timestamp creationDate) {
    this.creationDate = creationDate;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;

    if (!(o instanceof NewsArticle)) return false;

    NewsArticle that = (NewsArticle) o;

    return new org.apache.commons.lang3.builder.EqualsBuilder()
        .append(title, that.title)
        .append(text, that.text)
        .append(relevance, that.relevance)
        .append(creationDate, that.creationDate)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
        .append(title)
        .append(text)
        .append(relevance)
        .append(creationDate)
        .toHashCode();
  }
}
