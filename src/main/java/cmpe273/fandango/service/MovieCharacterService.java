package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieCharacterDto;
import cmpe273.fandango.entity.MovieCharacter;

import java.util.List;

public interface MovieCharacterService {

  List<MovieCharacter> getMovieCharacters(Integer movieId);

  List<MovieCharacter> addCharacter(MovieCharacterDto movieCharacterDto);

  List<MovieCharacter> removeCharacter(MovieCharacterDto movieCharacterDto);

}
