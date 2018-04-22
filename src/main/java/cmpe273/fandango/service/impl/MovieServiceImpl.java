package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.*;
import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.*;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class MovieServiceImpl implements MovieService {

  @Autowired
  private MovieDao movieDao;

  @Autowired
  private MpaaRatingDao mpaaRatingDao;

  @Autowired
  private FormatDao formatDao;

  @Autowired
  private GenreDao genreDao;

  private MovieMapper movieMapper = new MovieMapper();

  @Override
  public MovieSimpleDto CreateMovie(MovieSimpleDto movieSimpleDto) {
    Movie movie = movieMapper.toPojo(movieSimpleDto);
    movie = updMpaaRating(movieSimpleDto, movie);
    movie = movieDao.save(movie);
    return movieMapper.toSimpleDto(movie);
  }

  @Override
  public MovieSimpleDto UpdateMovie(Integer movieId, MovieSimpleDto dto) {
    Movie movie = movieDao.findOne(movieId);
    if ( movie == null ) return null;
    movie = movieMapper.updPojo(dto, movie);
    movie = updMpaaRating(dto, movie);
    movieDao.save(movie);
    return movieMapper.toSimpleDto(movie);
  }

  @Override
  public Page<MovieSimpleDto> getAllMovies(Pageable pageable, Float minStars, Float maxStars, Integer genreId) {

    Page<Movie> movies;
    minStars = minStars == null ? 0.0f : minStars;
    maxStars = maxStars == null ? 5.0f : maxStars;

    if (genreId == null) {
      movies =  movieDao.findAllBy(pageable, minStars, maxStars);
    } else {
      movies =  movieDao.SelectAllMoviesByGenre(pageable, genreId, minStars, maxStars);
    }

    return new PageImpl<>(StreamSupport
        .stream(movies.spliterator(), false)
        .map(movieMapper::toSimpleDto).collect(Collectors.toList()));

  }

  @Override
  public MovieDto getMovie(Integer movieId) {
    Movie movie = movieDao.findOne(movieId);
    if (movie != null) {
      MovieDto movieDto = movieMapper.toDto(movie);
      return movieDto;
    }
    return null;
  }

  private Movie updMpaaRating(MovieSimpleDto dto, Movie movie){
    if ( dto.getMpaaId() != null){
      MpaaRating mpaaRating = mpaaRatingDao.findOne(dto.getMpaaId());
      if (mpaaRating != null)
        movie.setMpaaRating(mpaaRating.getMpaaName());
    }
    return movie;
  }

  @Override
  public MovieDto addFormat(MovieFormatDto movieFormatDto) {
    Format format = formatDao.findOne(movieFormatDto.getFormatId());
    Movie movie = movieDao.findOne(movieFormatDto.getMovieId());
    if ( format == null || movie == null) return null;
    if ( !movie.getFormats().contains(format)) {
      movie.getFormats().add(format);
      movieDao.save(movie);
    }
    return movieMapper.toDto(movie);
  }

  @Override
  public MovieDto removeFormat(MovieFormatDto movieFormatDto) {
    Format format = formatDao.findOne(movieFormatDto.getFormatId());
    Movie movie = movieDao.findOne(movieFormatDto.getMovieId());
    if ( format == null || movie == null) return null;
    if ( movie.getFormats().contains(format)) {
      movie.getFormats().remove(format);
      movieDao.save(movie);
    }
    return movieMapper.toDto(movie);
  }

  @Override
  public MovieDto addGenre(MovieGenreDto movieGenreDto) {
    Genre genre = genreDao.findOne(movieGenreDto.getGenreId());
    Movie movie = movieDao.findOne(movieGenreDto.getMovieId());
    if ( genre == null || movie == null) return null;
    if ( !movie.getGenres().contains(genre)) {
      movie.getGenres().add(genre);
      movieDao.save(movie);
    }
    return movieMapper.toDto(movie);
  }

  @Override
  public MovieDto removeGenre(MovieGenreDto movieGenreDto) {
    Genre genre = genreDao.findOne(movieGenreDto.getGenreId());
    Movie movie = movieDao.findOne(movieGenreDto.getMovieId());
    if ( genre == null || movie == null) return null;
    if ( movie.getGenres().contains(genre)) {
      movie.getGenres().remove(genre);
      movieDao.save(movie);
    }
    return movieMapper.toDto(movie);
  }
}
