package cmpe273.fandango.mapper.impl;

import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.mapper.GenericMapper;
import cmpe273.fandango.mapper.TheaterMapper;
import org.springframework.stereotype.Component;

@Component
public class TheaterMapperImpl extends GenericMapper implements TheaterMapper {

  @Override
  public Theater toPojo ( ParamCreateTheater dto) {
    return mapT1toT2(dto, new Theater());
  }

  @Override
  public TheaterDto toDto(Theater pojo) {
    TheaterDto dto = mapT1toT2(pojo, new TheaterDto());
    dto.setCity(pojo.getCity().getCityName());
    dto.setState(pojo.getCity().getState());
    return dto;
  }

  @Override
  public SchedulePerTheaterDto toPerTheaterDto (Theater pojo) {
    return mapT1toT2(pojo, new SchedulePerTheaterDto());
  }

  @Override
  public SchedulePerMovieDto toPerMovieDto(Movie pojo) {
    return mapT1toT2(pojo, new SchedulePerMovieDto());
  }

  @Override
  public TheaterMovieTodayDto toTheaterMovieTodayDto (Theater pojo) {return mapT1toT2(pojo, new TheaterMovieTodayDto());}

  @Override
  public ScheduleAllTheaterMovieDto toSceduleAllTheaterMovieDto (Theater pojo) {
    return mapT1toT2(pojo, new ScheduleAllTheaterMovieDto());
  }

  @Override
  public Theater updPojo(Theater pojo, ParamUpdateTheater dto) {
    if ( dto == null ) return pojo;
    updateValue(pojo::setStreet, dto.getStreet());
    updateValue(pojo::setTheaterName, dto.getTheaterName());
    updateValue(pojo::setZipcode, dto.getZipcode());
    return pojo;
  }
}
