package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GenreDao extends CrudRepository<Genre, Integer> {

  @Query("select g.genreId,  g.genreName from Genre g join g.movieGenres mg where mg.movieId = :movieId")
  List<Genre> selectMoiveGenreList(@Param("movieId") Integer movieId);

}
