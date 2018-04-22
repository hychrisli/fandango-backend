package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.MovieDao;
import cmpe273.fandango.dao.MovieImageDao;
import cmpe273.fandango.dto.MovieImageDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.MovieImage;
import cmpe273.fandango.mapper.MovieImageMapper;
import cmpe273.fandango.service.MovieImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieImageServiceImpl implements MovieImageService {

  @Autowired
  MovieImageDao movieImageDao;

  @Autowired
  MovieDao movieDao;

  MovieImageMapper movieImageMapper = new MovieImageMapper();

  @Override
  public List<MovieImage> getAllMovieImages(Integer movieId) {
    return movieImageDao.findAllByMovieId(movieId);
  }

  @Override
  public List<MovieImage> addMovieImage(MovieImageDto movieImageDto) {
    Movie movie = movieDao.findOne(movieImageDto.getMovieId());
    if ( movie == null) return null;
    MovieImage movieImage = movieImageMapper.toPojo(movieImageDto);
    movieImage.setMovie(movie);
    movieImageDao.save(movieImage);
    return movieImageDao.findAllByMovieId(movieImageDto.getMovieId());
  }

  @Override
  public List<MovieImage> removeMovieImage(MovieImageDto movieImageDto) {
    Movie movie = movieDao.findOne(movieImageDto.getMovieId());
    MovieImage movieImage = movieImageDao.findOne(movieImageDto.getImageId());
    if ( movie == null || movieImage == null) return null;
    if ( movieImage.getMovie() == movie)
      movieImageDao.delete(movieImage);

    return movieImageDao.findAllByMovieId(movieImageDto.getMovieId());
  }
}
