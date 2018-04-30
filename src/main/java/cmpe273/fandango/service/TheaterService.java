package cmpe273.fandango.service;

import cmpe273.fandango.dto.ParamCreateTheater;
import cmpe273.fandango.dto.ParamUpdateTheater;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TheaterService {

  Page<Theater> getAllTheatersByCityId(Integer cityId, Pageable pageable);

  Theater addTheater(ParamCreateTheater theaterDto) throws AppException;

  Theater getTheater(Integer theaterId);

  Theater updateTheater(Integer theaterId, ParamUpdateTheater param) throws AppException;

  Boolean removeTheater(Integer theaterId);

  Page<Theater> searchTheatersByPattern(String pattern, Pageable pageable);



}
