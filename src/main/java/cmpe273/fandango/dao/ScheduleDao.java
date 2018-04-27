package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleDao extends PagingAndSortingRepository<Schedule, Long> {

  @Query("select count(s) from Schedule s where s.theater.theaterId = :theaterId")
  Integer countAllByTheaterId(@Param("theaterId") Integer theaterId);

  @Query("select s from Schedule s where " +
      "s.theater.city.cityId = :cityId and " +
      "s.movie.movieId = :movieId and " +
      "s.scheduleDate = :today")
  List<Schedule> findMovieScheduleByCityId (
      @Param("cityId") Integer cityId,
      @Param("movieId") Integer movieId,
      @Param("today") Date today);

  @Query("select s from Schedule s where " +
      "s.theater.zipcode = :zipcode and " +
      "s.movie.movieId = :movieId and " +
      "s.scheduleDate = :today")
  List<Schedule> findMovieScheduleByZipcode (
      @Param("zipcode") String zipcode,
      @Param("movieId") Integer movieId,
      @Param("today") Date today);


  @Query("select s from Schedule s where " +
      "s.theater.city.cityId = :cityId and " +
      "s.scheduleDate = :today " +
      "order by s.theater.theaterId, s.movie.releaseDate desc, s.showtime")
  List<Schedule> findAllScheduleByCityId (
      @Param("cityId") Integer cityId,
      @Param("today") Date today);

  @Query("select s from Schedule s where " +
      "s.theater.zipcode = :zipcode and " +
      "s.scheduleDate = :today " +
      "order by s.theater.theaterId, s.movie.releaseDate desc, s.showtime" )
  List<Schedule> findAllScheduleByZipcode (
      @Param("zipcode") String zipcode,
      @Param("today") Date today);


  @Query("select s from Schedule s where " +
      "s.theater.theaterId = :theaterId and " +
      "s.scheduleDate = :today")
  List<Schedule> findMovieSchedulesByTheaterId (
      @Param("theaterId") Integer theaterId,
      @Param("today") Date today);

  @Query("select s from Schedule s " +
      "where s.price >= :minPrice and s.price <= :maxPrice " +
      "and s.movie.stars >= :minStars and s.movie.stars <= :maxStars " +
      "and s.movie.mpaaId in :mpaaIds and s.format.formatId in :formatIds " +
      "and s.theater.city.cityId = :cityId and s.scheduleDate = :today")
  List<Schedule> searchMovie (
      @Param("minPrice") Float minPrice,
      @Param("maxPrice") Float maxPrice,
      @Param("minStars") Float minStars,
      @Param("maxStars") Float maxStars,
      @Param("mpaaIds") List<Integer> mpaaIds,
      @Param("formatIds") List<Integer> formatIds,
      @Param("cityId") Integer cityId,
      @Param("today") Date today
  );

  @Query("select distinct s.theater, s.movie from Schedule s " +
      "where s.theater.zipcode = :zipcode and s.scheduleDate = :today " +
      "order by s.movie.releaseDate desc")
  List<Object> findTheatersWithMoviesTodayByZipcode(
      @Param("zipcode") String zipcode,
      @Param("today") Date today);

  @Query("select distinct s.theater, s.movie from Schedule s " +
      "where s.theater.city.cityId = :cityId and s.scheduleDate = :today " +
      "order by s.movie.releaseDate desc")
  List<Object> findTheatersWithMoviesTodayByCityId(
      @Param("cityId") Integer cityId,
      @Param("today") Date today);

}
