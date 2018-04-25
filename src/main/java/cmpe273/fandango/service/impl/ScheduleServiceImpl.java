package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.ScheduleDao;
import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Schedule;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.lib.DateTime;
import cmpe273.fandango.lib.Pagintation;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.mapper.ScheduleMapper;
import cmpe273.fandango.mapper.TheaterMapper;
import cmpe273.fandango.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  ScheduleDao scheduleDao;

  private TheaterMapper theaterMapper = new TheaterMapper();

  private ScheduleMapper scheduleMapper = new ScheduleMapper();

  private MovieMapper movieMapper = new MovieMapper();

  @Override
  public List<TheaterScheduleDto> getNearByMovieSchedule(Integer cityId, Integer movieId) {

    Date today = DateTime.getToday();
    List<Schedule> schedules = scheduleDao.findNearByMovieSchedule(cityId, movieId, today);
    Map<Integer, TheaterScheduleDto> lkp = new HashMap<>();

    for (Schedule s : schedules) {
      Integer key = s.getTheater().getTheaterId();
      TheaterScheduleDto tsd = lkp.getOrDefault(key,
          theaterMapper.toTheaterScheduleDto(s.getTheater()));
      if (tsd.getSchedules() == null) tsd.setSchedules(new ArrayList<>());
      tsd.getSchedules().add(scheduleMapper.toScheduleSimpleDto(s));
      lkp.put(key, tsd);
    }
    return new ArrayList<>(lkp.values());
  }

  @Override
  public Page<MovieSearchDto> searchMovies(Pageable pageable, Integer cityId, ParamSearchMovie dto) {

    List<Integer> mpaaIds = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> formatIds = Arrays.asList(1, 2, 3, 4);
    if ( dto.getMpaaId() != null ) mpaaIds = Arrays.asList(dto.getMpaaId());
    if ( dto.getFormatId() != null ) formatIds = Arrays.asList(dto.getFormatId());

    List<Schedule> schedules = scheduleDao.searchMovie(
        dto.getMinPrice(),
        dto.getMaxPrice(),
        dto.getMinStars(),
        dto.getMaxStars(),
        mpaaIds,
        formatIds,
        cityId,
        DateTime.getToday()
    );

    Map<Movie, MovieSearchDto> movieMap = new HashMap<>();
    for (Schedule s : schedules){
      Movie key = s.getMovie();
      String formatName = s.getFormat().getFormatName();
      MovieSearchDto movieSearchDto = movieMap.getOrDefault(key, movieMapper.toSearchDto(key));

      if ( movieSearchDto.getFormatNames() == null )
        movieSearchDto.setFormatNames(new ArrayList<>());
      if ( ! movieSearchDto.getFormatNames().contains(formatName) )
        movieSearchDto.getFormatNames().add(formatName);

      movieSearchDto.setMinPrice(Math.min(movieSearchDto.getMinPrice(), s.getPrice()));
      movieMap.put(key, movieSearchDto);
    }

    List<MovieSearchDto> movieList = new ArrayList<>(movieMap.values());
    Collections.sort(movieList);

    return Pagintation.getPage(movieList, pageable);
  }

  @Override
  public Page<TheaterMovieTodayDto> getTheaterMovieTodayByZipcode(Pageable pageable, String zipcode) {
    List<Object> rows = scheduleDao.findTheatersWithMoviesTodayByZipcode(zipcode, DateTime.getToday());
    Map<Integer, TheaterMovieTodayDto> theaterMap = new HashMap<>();

    for (Object row : rows ) {
      Object[] fields = (Object[]) row;
      Theater theater = (Theater) fields[0];
      Movie movie = (Movie) fields[1];

      Integer key = theater.getTheaterId();
      TheaterMovieTodayDto tmtDto = theaterMap.getOrDefault(key, theaterMapper.toTheaterMovieTodayDto(theater));
      if (tmtDto.getMovies() == null)
        tmtDto.setMovies(new ArrayList<>());
      tmtDto.getMovies().add(movie);
      theaterMap.put(key, tmtDto);
    }

    List<TheaterMovieTodayDto> theaterList = new ArrayList<>(theaterMap.values());
    Collections.sort(theaterList);

    return Pagintation.getPage(theaterList, pageable);
  }
}
