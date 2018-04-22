package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MovieDao extends PagingAndSortingRepository<Movie, Integer> {

  Page<Movie> findAllBy(Pageable pageable);
}
