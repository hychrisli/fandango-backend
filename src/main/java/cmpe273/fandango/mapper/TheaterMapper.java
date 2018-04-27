package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Theater;

public interface TheaterMapper {

  Theater toPojo (ParamCreateTheater dto);

  TheaterDto toDto (Theater pojo);

  SchedulePerTheaterDto toPerTheaterDto (Theater pojo);

  SchedulePerMovieDto toPerMovieDto(Movie pojo);

  TheaterMovieTodayDto toTheaterMovieTodayDto (Theater pojo);

  ScheduleAllTheaterMovieDto toSceduleAllTheaterMovieDto (Theater pojo);
}

