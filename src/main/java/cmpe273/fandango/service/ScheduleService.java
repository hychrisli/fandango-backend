package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Schedule;

import java.util.List;

public interface ScheduleService {

  List<Schedule> getNearByMovieSchedule(Integer cityId, Integer movieId);

  List<MovieSimpleDto> searchNearbyAvailableMovie();

}
