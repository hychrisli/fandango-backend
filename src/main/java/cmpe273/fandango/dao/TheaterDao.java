package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Theater;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheaterDao extends CrudRepository<Theater, Integer>{


  @Query("select t from Theater t where t.city.cityId = :cityId")
  List<Theater> findAllByCityId(@Param("cityId") Integer cityId);

  @Query("select count(t) from Theater t where t.city.cityId = :cityId")
  Integer countTheaterByCityId(@Param("cityId") Integer cityId);

  @Query("select t from Theater t where lower(t.theaterName) = lower(:theaterName) and t.city.cityId = :cityId")
  Theater findByTheaterNameAndCityId(@Param("theaterName") String theaterName, @Param("cityId") Integer cityId );

}