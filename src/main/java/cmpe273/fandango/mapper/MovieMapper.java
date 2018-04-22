package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;
import org.modelmapper.convention.MatchingStrategies;

public class MovieMapper extends AbstractMapper<Movie, MovieDto> {

  public MovieMapper(){
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  @Override
  public MovieDto toDto(Movie pojo) {
    if ( pojo == null) return null;
    return modelMapper.map(pojo,MovieDto.class);
  }

  public MovieSimpleDto toSimpleDto(Movie pojo){
    if (pojo == null) return null;
    return modelMapper.map(pojo, MovieSimpleDto.class);
  }

  @Override
  public Movie toPojo(MovieDto movieDto) {
    return null;
  }
}
