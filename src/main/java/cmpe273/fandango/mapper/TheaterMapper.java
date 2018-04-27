package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.*;
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

  public ScheduleAllTheaterMovieDto toSceduleAllTheaterMovieDto (Theater pojo) {
    return mapT1toT2(pojo, new ScheduleAllTheaterMovieDto());
  }
}
