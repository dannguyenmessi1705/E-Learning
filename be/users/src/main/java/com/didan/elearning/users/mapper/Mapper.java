package com.didan.elearning.users.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Mapper {
  private static final ModelMapper modelMapper = new ModelMapper();
  public Mapper() {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }
  /**
    * Maps the entity to the DTO.
    * @param entity the entity to map
    * @param outClass the DTO class
    * @return the mapped DTO
  */
  public static <D, T> D mapToDto(T entity, Class<D> outClass) {
    return modelMapper.map(entity, outClass);
  }
}
