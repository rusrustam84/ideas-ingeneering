package com.rustamk.ideasingeneering.web.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseDto {
  @JsonProperty("id")
  private Long id;

  public BaseDto(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
