package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Sales;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface SalesDao extends PagingAndSortingRepository<Sales, Integer> {

  @Query("select sum(s.dollarAmount), sum(s.ticketNum) from Sales s where s.movie.movieId = :movieId")
  Object findByMovieId(@Param("movieId") Integer movieId);

  @Query("select sum(s.dollarAmount), sum(s.ticketNum) from Sales s where s.theater.theaterId = :theaterId")
  Object findByTheaterId(@Param("theaterId") Integer theaterId);

  @Query("select s.theater, sum(s.dollarAmount), sum(s.ticketNum) from Sales s group by s.theater order by sum(s.dollarAmount) desc")
  Page<Object> findTop10TheaterRevenue(Pageable pageable);

  @Query("select s.movie, sum(s.dollarAmount), sum(s.ticketNum) from Sales s group by s.movie order by sum(s.dollarAmount) desc")
  Page<Object> findTop10MovieRevenue(Pageable pageable);

  @Query("select s.movie, sum(s.dollarAmount), sum(s.ticketNum) from Sales s group by s.movie order by sum(s.ticketNum) desc")
  Page<Object> findTop10MovieTicketSales(Pageable pageable);

}
