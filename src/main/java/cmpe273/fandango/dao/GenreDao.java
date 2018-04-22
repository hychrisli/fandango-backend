package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreDao extends CrudRepository<Genre, Integer> {
  List<Genre> findAll();

  @Query("select g from Genre g join g.movies gm where gm.movieId = :movieId")
  List<Genre> findAllByMovieId(@Param("movieId") Integer movieId);
}
