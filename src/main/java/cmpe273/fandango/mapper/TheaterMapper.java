package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.SchedulePerMovieDto;
import cmpe273.fandango.dto.TheaterDto;
import cmpe273.fandango.dto.TheaterMovieTodayDto;
import cmpe273.fandango.dto.SchedulePerTheaterDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Theater;

public class TheaterMapper extends GenericMapper{

  public Theater toPojo ( TheaterDto dto) {
    return mapT1toT2(dto, new Theater());
  }

  public SchedulePerTheaterDto toPerTheaterDto (Theater pojo) {
    return mapT1toT2(pojo, new SchedulePerTheaterDto());
  }

  public SchedulePerMovieDto toPerMovieDto(Movie pojo) {
    return mapT1toT2(pojo, new SchedulePerMovieDto());
  }

  public TheaterMovieTodayDto toTheaterMovieTodayDto (Theater pojo) {return mapT1toT2(pojo, new TheaterMovieTodayDto());}

}
