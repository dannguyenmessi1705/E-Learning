package com.didan.elearning.users.mapper;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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

  /**
    * Maps the iterable source to the list of destination objects.
    * @param source the source object
    * @param outCLass the destination class
    * @return the list of destination objects
  */
  public static <S, D> List<D> mapList(Iterable<S> source, Class<D> outCLass) {
    return StreamSupport.stream(source.spliterator(), false)
      .map(element -> map(element, outCLass))
      .toList();
  }
}
