package cmpe273.fandango.service;

import cmpe273.fandango.entity.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CityService {

  List<City> getAvailCities();

  Page<City> searchCitiesByPattern(String pattern, Pageable pageable);

  City addCity(City city);

  Boolean removeCity(Integer cityId);

}
