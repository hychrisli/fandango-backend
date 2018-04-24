package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieSearchDto;
import cmpe273.fandango.dto.ParamSearchMovie;
import cmpe273.fandango.dto.TheaterScheduleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {

  List<TheaterScheduleDto> getNearByMovieSchedule(Integer cityId, Integer movieId);

  Page<MovieSearchDto> searchMovies(Pageable pageable, Integer cityId, ParamSearchMovie paramSearchMovie);
}
