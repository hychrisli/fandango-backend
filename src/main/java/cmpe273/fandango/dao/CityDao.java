package cmpe273.fandango.dao;

import cmpe273.fandango.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityDao extends PagingAndSortingRepository<City, Integer> {
  List<City> findAllBy();

  @Query("select c from City c where lower(c.cityName) = lower(:cityName) and upper(c.state) = upper(:state)")
  City findCityByNameAndState(@Param("cityName") String cityName, @Param("state") String state);

  @Query("select c from City c where lower(c.cityName) like lower(concat('%',:pattern,'%'))")
  Page<City> searchCityByPattern(@Param("pattern") String pattern, Pageable pageable);

}
