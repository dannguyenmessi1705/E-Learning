package com.didan.elearning.users.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Mapper {
  private static final ModelMapper modelMapper;
  static {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }
  private Mapper() {}
  /**
    * Maps the entity to the DTO.
    * @param entity the entity to map
    * @param outClass the DTO class
    * @return the mapped DTO
  */
  public static <D, T> D map(T entity, Class<D> outClass) {
    return modelMapper.map(entity, outClass);
  }

  /**
    * Maps the source to the destination.
    * @param source the source object
    * @param destination the destination object
  */
  public static <S, D> D map(S source, D destination) {
    modelMapper.map(source, destination);
    return destination;
  }
}
