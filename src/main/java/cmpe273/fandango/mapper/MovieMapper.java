package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.entity.Movie;

public class MovieMapper extends AbstractMapper<Movie, MovieDto> {


  @Override
  public MovieDto toDto(Movie pojo) {
    if ( pojo == null) return null;
    return modelMapper.map(pojo,MovieDto.class);
  }

  @Override
  public Movie toPojo(MovieDto movieDto) {
    return null;
  }
}
