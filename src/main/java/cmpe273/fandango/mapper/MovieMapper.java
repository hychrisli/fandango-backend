package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSearchDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;

public interface MovieMapper {

  public MovieDto toDto(Movie pojo);

  public MovieSimpleDto toSimpleDto(Movie pojo);

  public MovieSearchDto toSearchDto(Movie pojo);

  public Movie toPojo(MovieDto dto);

  public Movie toPojo(MovieSimpleDto dto);

  public Movie updPojo (MovieSimpleDto dto, Movie pojo);

}
