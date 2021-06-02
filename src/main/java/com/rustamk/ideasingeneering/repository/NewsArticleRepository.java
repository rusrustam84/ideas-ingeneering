package com.rustamk.ideasingeneering.repository;

import com.rustamk.ideasingeneering.domain.NewsArticle;
import org.springframework.data.repository.CrudRepository;

public interface NewsArticleRepository extends CrudRepository<NewsArticle, Long> {

}
