package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.CityDao;
import cmpe273.fandango.dao.TheaterDao;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService{

  @Autowired
  CityDao cityDao;

  @Autowired
  TheaterDao theaterDao;

  @Override
  public List<City> getAvailCities() {
    return cityDao.findAllBy();
  }

  @Override
  public City addCity(City city) {
    City curCity = cityDao.findCityByNameAndState(city.getCityName(), city.getState());
    if ( curCity != null) return null;
    return cityDao.save(city);
  }

  @Override
  public Boolean removeCity(Integer cityId) {
    Integer theaterCount = theaterDao.countTheaterByCityId(cityId);
    if ( theaterCount > 0 ) return false;
    cityDao.delete(cityId);
    return true;
  }
}
