package cmpe273.fandango.service;

import cmpe273.fandango.entity.City;

import java.util.List;

public interface CityService {

  List<City> getAvailCities();

  City addCity(City city);

  Boolean removeCity(Integer cityId);
}
