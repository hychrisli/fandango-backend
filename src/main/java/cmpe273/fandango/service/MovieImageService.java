package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieImageDto;
import cmpe273.fandango.entity.MovieImage;

import java.util.List;

public interface MovieImageService {

  List<MovieImage> getAllMovieImages (Integer movidId);

  List<MovieImage> addMovieImage(MovieImageDto movieImageDto);

  List<MovieImage> removeMovieImage(MovieImageDto movieImageDto);

}
