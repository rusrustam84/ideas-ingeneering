package com.rustamk.ideasingeneering.service.mapservice.util;

import com.rustamk.ideasingeneering.web.model.NewsArticleDto;
import com.rustamk.ideasingeneering.web.model.RelevanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Scope("singleton")
public class CSVNewsArticleDataUtil {

  public List<NewsArticleDto>  fetchData() {
    List<NewsArticleDto> tempList = new ArrayList<>();
    try {
      File inputFile = new ClassPathResource("data.csv").getFile();
      InputStream inputStream = new FileInputStream(inputFile);
      BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
      tempList = br.lines().map(mapToNewsArticle).collect(Collectors.toList());
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      e.getMessage();
    } finally {
      return tempList;
    }
  }

  private Function<String, NewsArticleDto> mapToNewsArticle = (line) -> {
    String[] l = line.split(";");
    NewsArticleDto newsArticle = new NewsArticleDto();
    for (int i = 0; i < l.length; i++) {
      if (i == 0) {
        newsArticle.setTitle(l[i].strip());
      } else if (i == 1) {
        newsArticle.setText(l[i].strip());
      } else if (i == 2) {
        String relevance = l[i].strip();
        RelevanceDto relevanceEnum;
        switch (relevance) {
          case "BORING": relevanceEnum = RelevanceDto.BORING;
          break;
          case "HOT": relevanceEnum = RelevanceDto.HOT;
          break;
          default: relevanceEnum = RelevanceDto.STANDARD;
        }

        newsArticle.setRelevance(relevanceEnum);
      } else if (i == 3) {
        newsArticle.setCreationDate(OffsetDateTime.now());
      }
    }
    return newsArticle;
  };


}
