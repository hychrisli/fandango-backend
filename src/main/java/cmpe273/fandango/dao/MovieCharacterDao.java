package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.MovieCharacter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieCharacterDao extends CrudRepository<MovieCharacter, Integer> {

  @Query("select mc from MovieCharacter mc where mc.movie.movieId = :movieId")
  List<MovieCharacter> findAllByMovieId(@Param("movieId") Integer movieId);

}
