package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Format;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormatDao extends CrudRepository<Format, Integer>{
  List<Format> findAll();

  @Query("select f from Format f join f.movies fm where fm.movieId = :movieId")
  List<Format> findAllByMovieId(@Param("movieId") Integer movieId);
}
