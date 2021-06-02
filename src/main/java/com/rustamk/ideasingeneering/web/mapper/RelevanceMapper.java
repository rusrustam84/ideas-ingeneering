package com.rustamk.ideasingeneering.web.mapper;

import com.rustamk.ideasingeneering.domain.Relevance;
import com.rustamk.ideasingeneering.web.model.RelevanceDto;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper
public interface RelevanceMapper {
  @ValueMappings({
      @ValueMapping(source = "STANDARD", target = "STANDARD"),
      @ValueMapping(source = "BORING", target = "BORING"),
      @ValueMapping(source = "HOT", target = "HOT")
  })
  Relevance dtoToEntity(RelevanceDto relevanceDto);

  @ValueMappings({
      @ValueMapping(source = "STANDARD", target = "STANDARD"),
      @ValueMapping(source = "BORING", target = "BORING"),
      @ValueMapping(source = "HOT", target = "HOT")
  })
  RelevanceDto entityToDto(Relevance relevance);

}
