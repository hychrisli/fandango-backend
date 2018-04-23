package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieCharacterDto;
import cmpe273.fandango.entity.MovieCharacter;

public class MovieCharacterMapper extends GenericMapper {

  public MovieCharacterDto toDto (MovieCharacter pojo){
    MovieCharacterDto dto = mapT1toT2(pojo, new MovieCharacterDto());
    dto.setMovieId(pojo.getMovie().getMovieId());
    return dto;
  }

}
