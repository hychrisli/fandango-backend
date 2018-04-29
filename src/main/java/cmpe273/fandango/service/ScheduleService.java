package cmpe273.fandango.service;

import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService {

  Page<SchedulePerTheaterDto> getMovieScheduleInTheatersByCityId(Integer cityId, Integer movieId, ParamFilterSchedule param, Pageable pageable);

  Page<SchedulePerTheaterDto> getMovieScheduleInTheatersByzipcode(String zipcode, Integer movieId, ParamFilterSchedule param, Pageable pageable);

  Page<ScheduleAllTheaterMovieDto> getAllScheduleInTheatersByCityId(Integer cityId, ParamFilterSchedule param, Pageable pageable);

  Page<ScheduleAllTheaterMovieDto> getAllScheduleInTheatersByzipcode(String zipcode, ParamFilterSchedule param, Pageable pageable);

  Page<SchedulePerMovieDto> getScheduleByTheaterId(Integer theaterId, ParamFilterSchedule param, Pageable pageable);

  Page<MovieSearchDto> searchMovies(Pageable pageable, Integer cityId, ParamSearchMovie paramSearchMovie);

  Page<TheaterMovieTodayDto> getTheaterMovieTodayByZipcode(String zipcode, Pageable pageable);

  Page<TheaterMovieTodayDto> getTheaterMovieTodayByCityId(Integer cityId, Pageable pageable);

  Schedule createSchedule(ParamCreateSchedule paramCreateSchedule);

  Schedule updateSchedule(Long scheduleId, ParamUpdateSchedule paramUpdateSchedule);

  Schedule getScheduleById(Long scheduleId);

  Boolean deleteSchedule(Long scheduleId);

}
