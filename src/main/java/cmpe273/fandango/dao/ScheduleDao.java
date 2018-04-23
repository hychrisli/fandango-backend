package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Schedule;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScheduleDao extends CrudRepository<Schedule, Long> {

  @Query("select count(s) from Schedule s where s.theater.theaterId = :theaterId")
  Integer countAllByTheaterId(@Param("theaterId") Integer theaterId);

}
