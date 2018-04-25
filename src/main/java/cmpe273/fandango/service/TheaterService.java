package cmpe273.fandango.service;

import cmpe273.fandango.dto.TheaterDto;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TheaterService {

  Page<Theater> getAllTheatersByCityId(Integer cityId, Pageable pageable);

  Theater addTheater(TheaterDto theaterDto);

  Boolean removeTheater(Integer theaterId);

}
