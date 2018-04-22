package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.entity.Genre;

import java.util.List;

public interface MovieGenreService {

  List<Genre> getAllGenres ();

  MovieGenreDto addGenre (Integer movieId, Integer genreId);

  MovieGenreDto removeGenre (Integer movieId, Integer genreId);

}
