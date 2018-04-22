package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {

  Boolean CreateMovie (Movie movie);

  Page<MovieSimpleDto> getAllMovies (Pageable pageable, Float minStars, Float maxStars, Integer genreId);

  MovieDto getMovie(Integer movieId);

}
