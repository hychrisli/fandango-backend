package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface MovieDao extends PagingAndSortingRepository<Movie, Integer> {

  @Query("select m from Movie m where m.stars >= :minStars and m.stars <= :maxStars")
  Page<Movie> findAllBy(Pageable pageable, @Param("minStars") Float minStars, @Param("maxStars") Float maxStars);

  @Query("select m from Movie m join m.genres mg where mg.genreId = :genreId and m.stars >= :minStars and m.stars <= :maxStars")
  Page<Movie> SelectAllMoviesByGenre(Pageable pageable, @Param("genreId") Integer genreId, @Param("minStars") Float minStars, @Param("maxStars") Float maxStars);

  @Query("select m from Movie m where lower(m.movieTitle) like lower(concat('%',:pattern,'%'))")
  Page<Movie> searchMoviesByPattern(@Param("pattern") String pattern, Pageable pageable);
}
