package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.MovieCharacterDao;
import cmpe273.fandango.dao.MovieDao;
import cmpe273.fandango.dto.MovieCharacterDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.MovieCharacter;
import cmpe273.fandango.mapper.MovieCharacterMapper;
import cmpe273.fandango.service.MovieCharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCharacterServiceImpl implements MovieCharacterService {

  @Autowired
  MovieDao movieDao;

  @Autowired
  MovieCharacterDao movieCharacterDao;

  private MovieCharacterMapper movieCharacterMapper = new MovieCharacterMapper();

  @Override
  public List<MovieCharacter> getMovieCharacters(Integer movieId) {
    Movie movie = movieDao.findOne(movieId);
    if (movie == null) return null;
    return movieCharacterDao.findAllByMovie(movie);
  }

  @Override
  public MovieCharacterDto addCharacter(MovieCharacterDto movieCharacterDto) {
    Movie movie = movieDao.findOne(movieCharacterDto.getMovieId());
    if ( movie == null ) return null;
    MovieCharacter movieCharacter = new MovieCharacter();
    movieCharacter.setCharacterName(movieCharacterDto.getCharacterName());
    movieCharacter.setMovie(movie);
    movieCharacter = movieCharacterDao.save(movieCharacter);
    return movieCharacterMapper.toDto(movieCharacter);
  }

  @Override
  public Boolean removeCharacter(MovieCharacterDto movieCharacterDto) {
    MovieCharacter movieCharacter = movieCharacterDao.findOne(movieCharacterDto.getCharacterId());
    if (movieCharacter.getMovie().getMovieId() == movieCharacterDto.getMovieId()) {
      movieCharacterDao.delete(movieCharacterDto.getCharacterId());
      return true;
    }
    return false;
  }
}
