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
  public Page<SchedulePerTheaterDto> getScheduleInTheatersByCityId(Integer cityId, Integer movieId, Pageable pageable) {
    List<Schedule> schedules = scheduleDao.findMovieScheduleByCityId(cityId, movieId, DateTime.getToday());
    return getSchedulePerTheaterToday(schedules, pageable);
  }

  @Override
  public Page<SchedulePerTheaterDto> getScheduleInTheatersByzipcode(String zipcode, Integer movieId, Pageable pageable) {
    List<Schedule> schedules = scheduleDao.findMovieScheduleByZipcode(zipcode, movieId, DateTime.getToday());
    return getSchedulePerTheaterToday(schedules, pageable);
  }

  @Override
  public Page<SchedulePerMovieDto> getScheduleByTheaterId(Integer theaterId, Pageable pageable) {
    List<Schedule> schedules = scheduleDao.findMovieSchedulesByTheaterId(theaterId, DateTime.getToday());
    return getSchuedulePerMovieToday(schedules, pageable);
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
  public Page<TheaterMovieTodayDto> getTheaterMovieTodayByZipcode(String zipcode, Pageable pageable) {
    List<Object> rows = scheduleDao.findTheatersWithMoviesTodayByZipcode(zipcode, DateTime.getToday());
    return getTheatersMovieToday(rows, pageable);
  }

  @Override
  public Page<TheaterMovieTodayDto> getTheaterMovieTodayByCityId(Integer cityId, Pageable pageable) {
    List<Object> rows = scheduleDao.findTheatersWithMoviesTodayByCityId(cityId, DateTime.getToday());
    return getTheatersMovieToday(rows, pageable);
  }

  private Page<TheaterMovieTodayDto> getTheatersMovieToday (List<Object> rows, Pageable pageable){
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

  private Page<SchedulePerTheaterDto> getSchedulePerTheaterToday(List<Schedule> schedules, Pageable pageable) {
    Map<Integer, SchedulePerTheaterDto> lkp = new HashMap<>();

    for (Schedule s : schedules) {
      Integer key = s.getTheater().getTheaterId();
      SchedulePerTheaterDto sptd = lkp.getOrDefault(key,
          theaterMapper.toPerTheaterDto(s.getTheater()));
      if (sptd.getSchedules() == null) sptd.setSchedules(new ArrayList<>());
      sptd.getSchedules().add(scheduleMapper.toScheduleSimpleDto(s));
      lkp.put(key, sptd);
    }

    List<SchedulePerTheaterDto> sptDtos=  new ArrayList<>(lkp.values());
    Collections.sort(sptDtos);

    return Pagintation.getPage(sptDtos, pageable);
  }

  private Page<SchedulePerMovieDto> getSchuedulePerMovieToday(List<Schedule> schedules, Pageable pageable) {
    Map<Integer, SchedulePerMovieDto> lkp = new HashMap<>();

    for ( Schedule s: schedules) {
      Integer key = s.getMovie().getMovieId();
      SchedulePerMovieDto spmd = lkp.getOrDefault(key, theaterMapper.toPerMovieDto(s.getMovie()));
      if (spmd.getSchedules() == null ) spmd.setSchedules(new ArrayList<>());
      spmd.getSchedules().add(scheduleMapper.toScheduleSimpleDto(s));
      lkp.put(key, spmd);

    }

    List<SchedulePerMovieDto> spmdDtos = new ArrayList<>(lkp.values());
    Collections.sort(spmdDtos);

    return Pagintation.getPage(spmdDtos, pageable);

  }
}
