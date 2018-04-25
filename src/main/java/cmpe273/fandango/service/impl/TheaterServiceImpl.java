package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.CityDao;
import cmpe273.fandango.dao.ScheduleDao;
import cmpe273.fandango.dao.TheaterDao;
import cmpe273.fandango.dto.TheaterDto;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.entity.Schedule;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.mapper.TheaterMapper;
import cmpe273.fandango.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

  @Autowired
  CityDao cityDao;

  @Autowired
  TheaterDao theaterDao;

  @Autowired
  ScheduleDao scheduleDao;

  private TheaterMapper theaterMapper = new TheaterMapper();

  @Override
  public Page<Theater> getAllTheatersByCityId(Integer cityId, Pageable pageable) {
    return theaterDao.findAllByCityId(cityId, pageable);
  }

  @Override
  public Theater addTheater(TheaterDto theaterDto) {
    Theater theater = theaterDao.findByTheaterNameAndCityId(theaterDto.getTheaterName(), theaterDto.getCityId());
    City city = cityDao.findOne(theaterDto.getCityId());
    if ( theater != null ) return null;
    theater = theaterMapper.toPojo(theaterDto);
    theater.setCity(city);
    return theaterDao.save(theater);
  }

  @Override
  public Boolean removeTheater(Integer theaterId) {
    Integer scheduleCount = scheduleDao.countAllByTheaterId(theaterId);
    if ( scheduleCount > 0 ) return false;
    theaterDao.delete(theaterId);
    return true;
  }

  @Override
  public Page<Theater> searchTheatersByPattern(String pattern, Pageable pageable) {
    return theaterDao.searchTheatersByPattern(pattern, pageable);
  }
}
