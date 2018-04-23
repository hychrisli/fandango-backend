package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.dto.TheaterScheduleDto;
import cmpe273.fandango.entity.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

  List<TheaterScheduleDto> getNearByMovieSchedule(Integer cityId, Integer movieId);

  List<MovieSimpleDto> searchNearbyAvailableMovie();

}
