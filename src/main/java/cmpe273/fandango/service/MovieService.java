package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MovieService {

  Boolean CreateMovie (Movie movie);

  Page<Movie> getAllMovies (Pageable pageable);

  MovieDto getMovie(Integer movieId);

}
