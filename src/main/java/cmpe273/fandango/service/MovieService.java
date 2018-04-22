package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {

  MovieSimpleDto CreateMovie (MovieSimpleDto movieSimpleDto);

  MovieSimpleDto UpdateMovie (Integer movieId, MovieSimpleDto movieSimpleDto);

  Page<MovieSimpleDto> getAllMovies (Pageable pageable, Float minStars, Float maxStars, Integer genreId);

  MovieDto getMovie(Integer movieId);

}
