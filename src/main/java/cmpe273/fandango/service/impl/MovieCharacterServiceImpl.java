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

  @Override
  public List<MovieCharacter> getMovieCharacters(Integer movieId) {
    return movieCharacterDao.findAllByMovieId(movieId);
  }

  @Override
  public List<MovieCharacter> addCharacter(MovieCharacterDto movieCharacterDto) {
    Movie movie = movieDao.findOne(movieCharacterDto.getMovieId());
    if ( movie == null ) return null;
    MovieCharacter movieCharacter = new MovieCharacter();
    movieCharacter.setCharacterName(movieCharacterDto.getCharacterName());
    movieCharacter.setMovie(movie);
    movieCharacterDao.save(movieCharacter);
    return  movieCharacterDao.findAllByMovieId(movieCharacterDto.getMovieId());
  }

  @Override
  public List<MovieCharacter> removeCharacter(MovieCharacterDto movieCharacterDto) {
    Movie movie = movieDao.findOne(movieCharacterDto.getMovieId());
    MovieCharacter movieCharacter = movieCharacterDao.findOne(movieCharacterDto.getCharacterId());
    if ( movie == null || movieCharacter == null) return null;
    if ( movie.getCharacters().contains(movieCharacter)){
      movieCharacterDao.delete(movieCharacterDto.getCharacterId());
     /* movie.getCharacters().remove(movieCharacter);
      movieDao.save(movie);*/
    }
    return movieCharacterDao.findAllByMovieId(movieCharacterDto.getMovieId());
  }
}
