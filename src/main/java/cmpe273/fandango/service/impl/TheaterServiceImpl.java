package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.CityDao;
import cmpe273.fandango.dao.ScheduleDao;
import cmpe273.fandango.dao.TheaterDao;
import cmpe273.fandango.dto.ParamCreateTheater;
import cmpe273.fandango.dto.ParamUpdateTheater;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.exception.AppException;
import cmpe273.fandango.lib.Validate;
import cmpe273.fandango.mapper.TheaterMapper;
import cmpe273.fandango.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static cmpe273.fandango.exception.ErrorCode.ERR_INVALID_ZIPCODE;

@Service
public class TheaterServiceImpl implements TheaterService {

  @Autowired
  CityDao cityDao;

  @Autowired
  TheaterDao theaterDao;

  @Autowired
  ScheduleDao scheduleDao;

  @Autowired
  TheaterMapper theaterMapper;


  @Override
  public Theater getTheater(Integer theaterId) {
    return theaterDao.findOne(theaterId);
  }

  @Override
  public Theater updateTheater(Integer theaterId, ParamUpdateTheater param) throws AppException {
    Theater theater = theaterDao.findOne(theaterId);
    if (theater == null) return null;
    if ( param.getZipcode() != null) {
      if (! Validate.isValidZipcode(param.getZipcode()))
        throw new AppException(ERR_INVALID_ZIPCODE, "Invalid zipcode. Failed to update theater");
      param.setZipcode(Validate.getMainZipcode(param.getZipcode()));
    }

    theater = theaterMapper.updPojo(theater, param);
    if ( param.getCityId() != null ){
      City city = cityDao.findOne(param.getCityId());
      if (city != null)
        theater.setCity(city);
    }

    return theaterDao.save(theater);
  }

  @Override
  public Page<Theater> getAllTheatersByCityId(Integer cityId, Pageable pageable) {
    return theaterDao.findAllByCityId(cityId, pageable);
  }

  @Override
  public Theater addTheater(ParamCreateTheater theaterDto) throws AppException {
    Theater theater = theaterDao.findByTheaterNameAndCityId(theaterDto.getTheaterName(), theaterDto.getCityId());
    City city = cityDao.findOne(theaterDto.getCityId());
    if ( theater != null ) return null;
    if (! Validate.isValidZipcode(theaterDto.getZipcode()))
      throw new AppException(ERR_INVALID_ZIPCODE, "Invalid zipcode. Failed to create theater");
    theaterDto.setZipcode(Validate.getMainZipcode(theaterDto.getZipcode()));
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
