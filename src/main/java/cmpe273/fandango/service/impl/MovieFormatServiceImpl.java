package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.FormatDao;
import cmpe273.fandango.dao.MovieDao;
import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.entity.Format;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.service.MovieFormatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieFormatServiceImpl implements MovieFormatService {

  @Autowired
  FormatDao formatDao;

  @Autowired
  MovieDao movieDao;

  private MovieMapper movieMapper = new MovieMapper();

  @Override
  public List<Format> getAllFormats() {
    return formatDao.findAll();
  }

  @Override
  public List<Format> getMovieFormats(Integer movieId) {
    return formatDao.findAllByMovieId(movieId);
  }

  @Override
  public List<Format> addFormat(MovieFormatDto movieFormatDto) {
    Format format = formatDao.findOne(movieFormatDto.getFormatId());
    Movie movie = movieDao.findOne(movieFormatDto.getMovieId());
    if ( format == null || movie == null) return null;
    if ( !movie.getFormats().contains(format)) {
      movie.getFormats().add(format);
      movieDao.save(movie);
    }
    return movie.getFormats();
  }

  @Override
  public List<Format> removeFormat(MovieFormatDto movieFormatDto) {
    Format format = formatDao.findOne(movieFormatDto.getFormatId());
    Movie movie = movieDao.findOne(movieFormatDto.getMovieId());
    if ( format == null || movie == null) return null;
    if ( movie.getFormats().contains(format)) {
      movie.getFormats().remove(format);
      movieDao.save(movie);
    }
    return movie.getFormats();
  }

}
