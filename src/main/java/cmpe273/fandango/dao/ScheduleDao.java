package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ScheduleDao extends CrudRepository<Schedule, Long> {

  @Query("select count(s) from Schedule s where s.theater.theaterId = :theaterId")
  Integer countAllByTheaterId(@Param("theaterId") Integer theaterId);

  @Query("select s from Schedule s where " +
      "s.theater.city.cityId = :cityId and " +
      "s.movie.movieId = :movieId and " +
      "s.scheduleDate = :today")
  List<Schedule> findNearByMovieSchedule (
      @Param("cityId") Integer cityId,
      @Param("movieId") Integer movieId,
      @Param("today") Date today);


}
