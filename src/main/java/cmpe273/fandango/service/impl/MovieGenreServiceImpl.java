package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.GenreDao;
import cmpe273.fandango.dao.MovieDao;
import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.entity.Genre;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.service.MovieGenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieGenreServiceImpl implements MovieGenreService{

  @Autowired
  GenreDao genreDao;

  @Autowired
  MovieDao movieDao;

  private MovieMapper movieMapper = new MovieMapper();

  @Override
  public List<Genre> getAllGenres() {
    return genreDao.findAll();
  }

  @Override
  public List<Genre> getMovieGenres(Integer movieId) {
    return genreDao.findAllByMovieId(movieId);
  }

  @Override
  public List<Genre> addGenre(MovieGenreDto movieGenreDto) {
    Genre genre = genreDao.findOne(movieGenreDto.getGenreId());
    Movie movie = movieDao.findOne(movieGenreDto.getMovieId());
    if ( genre == null || movie == null) return null;
    if ( !movie.getGenres().contains(genre)) {
      movie.getGenres().add(genre);
      movieDao.save(movie);
    }
    return movie.getGenres();
  }

  @Override
  public List<Genre> removeGenre(MovieGenreDto movieGenreDto) {
    Genre genre = genreDao.findOne(movieGenreDto.getGenreId());
    Movie movie = movieDao.findOne(movieGenreDto.getMovieId());
    if ( genre == null || movie == null) return null;
    if ( movie.getGenres().contains(genre)) {
      movie.getGenres().remove(genre);
      movieDao.save(movie);
    }
    return movie.getGenres();
  }
}
