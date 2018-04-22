package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.*;
import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.entity.*;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

  @Autowired
  private MovieDao movieDao;

  @Autowired
  private GenreDao genreDao;

  @Autowired
  private FormatDao formatDao;

  @Autowired
  private MovieCharacterDao movieCharacterDao;

  @Autowired
  private MovieImageDao movieImageDao;

  private MovieMapper movieMapper = new MovieMapper();

  @Override
  public Boolean CreateMovie(Movie movie) {
    return null;
  }

  @Override
  public Page<Movie> getAllMovies(Pageable pageable) {
    return movieDao.findAllBy(pageable);
  }

  @Override
  public MovieDto getMovie(Integer movieId) {
    Movie movie = movieDao.findOne(movieId);
    if (movie != null) {
      MovieDto movieDto = movieMapper.toDto(movie);
      movieDto.setGenres(genreDao.selectMoiveGenreList(movieId));
      movieDto.setFormats(formatDao.selectMoiveFormatList(movieId));
      movieDto.setCharacters(movieCharacterDao.findAllByMovieId(movieId));
      movieDto.setImages(movieImageDao.findAllByMovieId(movieId));
      return movieDto;
    }

    return null;
  }
}
