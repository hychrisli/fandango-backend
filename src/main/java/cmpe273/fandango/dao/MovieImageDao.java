package cmpe273.fandango.dao;

import cmpe273.fandango.entity.MovieImage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieImageDao extends CrudRepository<MovieImage, Integer>{

  @Query("select mi from MovieImage mi where mi.movie.movieId = :movieId")
  List<MovieImage>  findAllByMovieId( @Param("movieId") Integer movieId);

}
