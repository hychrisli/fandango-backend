package cmpe273.fandango.service;

import cmpe273.fandango.dto.TheaterDto;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.entity.Theater;

import java.util.List;

public interface TheaterService {

  List<Theater> getAllTheatersByCityId(Integer cityId);

  Theater addTheater(TheaterDto theaterDto);

  Boolean removeTheater(Integer theaterId);

}
