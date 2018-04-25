package cmpe273.fandango.service;

import cmpe273.fandango.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {

  Page<SchedulePerTheaterDto> getScheduleInTheatersByCityId(Integer cityId, Integer movieId, Pageable pageable);

  Page<SchedulePerTheaterDto> getScheduleInTheatersByzipcode(String zipcode, Integer movieId, Pageable pageable);

  Page<SchedulePerMovieDto> getScheduleByTheaterId(Integer theaterId, Pageable pageable);

  Page<MovieSearchDto> searchMovies(Pageable pageable, Integer cityId, ParamSearchMovie paramSearchMovie);

  Page<TheaterMovieTodayDto> getTheaterMovieTodayByZipcode(String zipcode, Pageable pageable);

  Page<TheaterMovieTodayDto> getTheaterMovieTodayByCityId(Integer cityId, Pageable pageable);

}
