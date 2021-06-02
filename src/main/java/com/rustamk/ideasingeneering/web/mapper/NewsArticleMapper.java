package com.rustamk.ideasingeneering.web.mapper;

import com.rustamk.ideasingeneering.domain.NewsArticle;
import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class, RelevanceMapper.class})
public interface NewsArticleMapper {

  NewsArticle dtoToEntity(NewsArticleDto newsArticleDto);

  NewsArticleDto entityToDto(NewsArticle newsArticle);
}
