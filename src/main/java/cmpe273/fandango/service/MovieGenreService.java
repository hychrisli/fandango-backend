package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.entity.Genre;

import java.util.List;

public interface MovieGenreService {

  List<Genre> getAllGenres();

  List<Genre> getMovieGenres (Integer movieId);

  List<Genre> addGenre (MovieGenreDto movieGenreDto);

  List<Genre> removeGenre (MovieGenreDto movieGenreDto);

}
