package cmpe273.fandango.service;

import cmpe273.fandango.dto.ParamCreateTheater;
import cmpe273.fandango.entity.Theater;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheaterService {

  Page<Theater> getAllTheatersByCityId(Integer cityId, Pageable pageable);

  Theater addTheater(ParamCreateTheater theaterDto);

  Boolean removeTheater(Integer theaterId);

  Page<Theater> searchTheatersByPattern(String pattern, Pageable pageable);



}
