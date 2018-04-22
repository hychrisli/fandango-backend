package cmpe273.fandango.service;

import cmpe273.fandango.entity.MovieImage;

import java.util.List;

public interface MovieImageService {

  List<MovieImage> getAllMovieImages ();

  MovieImage addMovieImage(MovieImage movieImage);

  Boolean removeMovieImage(Integer movieId, Integer imageId);

}
