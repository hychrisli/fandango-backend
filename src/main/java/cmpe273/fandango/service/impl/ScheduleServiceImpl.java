package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.FormatDao;
import cmpe273.fandango.dao.MovieDao;
import cmpe273.fandango.dao.ScheduleDao;
import cmpe273.fandango.dao.TheaterDao;
import cmpe273.fandango.dao.filter.ScheduleSpecs;
import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.Format;
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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  ScheduleDao scheduleDao;

  @Autowired
  MovieDao movieDao;

  @Autowired
  TheaterDao theaterDao;

  @Autowired
  FormatDao formatDao;

  @Autowired
  MovieMapper movieMapper;

  @Autowired
  TheaterMapper theaterMapper;

  @Autowired
  ScheduleMapper scheduleMapper;

  @Override
  public Schedule createSchedule(ParamCreateSchedule param) {
    Movie movie = movieDao.findOne(param.getMovieId());
    Theater theater = theaterDao.findOne(param.getTheaterId());
    Format format = formatDao.findOne(param.getFormatId());

    if ( movie == null || theater == null || format == null) return null;
    if ( ! movie.getFormats().contains(format)) return null;

    Schedule schedule = scheduleMapper.toPojo(param);
    schedule.setMovie(movie);
    schedule.setTheater(theater);
    schedule.setFormat(format);

    return scheduleDao.save(schedule);
  }

  @Override
  public Schedule updateSchedule(Long scheduleId, ParamUpdateSchedule param) {
    Schedule schedule = scheduleDao.findOne(scheduleId);
    if (schedule == null) return null;
    schedule = scheduleMapper.updPojo(param, schedule);
    return scheduleDao.save(schedule);
  }

  @Override
  public Schedule getScheduleById(Long scheduleId) {
    return scheduleDao.findOne(scheduleId);
  }

  @Override
  public Boolean deleteSchedule(Long scheduleId) {
    Schedule schedule = scheduleDao.findOne(scheduleId);
    if ( schedule == null) return false;
    scheduleDao.delete(scheduleId);
    return true;
  }

  @Override
  public Page<SchedulePerTheaterDto> getMovieScheduleInTheatersByCityId(Integer cityId, Integer movieId, ParamFilterSchedule param,Pageable pageable) {
    ScheduleSpecs specs = new ScheduleSpecs(param);
    specs.setCityId(cityId);
    specs.setMovieId(movieId);
    List<Schedule> schedules = scheduleDao.findAll(specs);
    return getSchedulePerTheater(schedules, pageable);
  }

  @Override
  public Page<SchedulePerTheaterDto> getMovieScheduleInTheatersByzipcode(String zipcode, Integer movieId, ParamFilterSchedule param, Pageable pageable) {
    ScheduleSpecs specs = new ScheduleSpecs(param);
    specs.setZipcode(zipcode);
    specs.setMovieId(movieId);
    List<Schedule> schedules = scheduleDao.findAll(specs);
    return getSchedulePerTheater(schedules, pageable);
  }

  @Override
  public Page<ScheduleAllTheaterMovieDto> getAllScheduleInTheatersByCityId(Integer cityId, ParamFilterSchedule param, Pageable pageable) {
    ScheduleSpecs specs = new ScheduleSpecs(param);
    specs.setCityId(cityId);
    specs.setOrdered(true);
    List<Schedule> schedules = scheduleDao.findAll(specs);
    return getAllSchedulePerTheater(schedules, pageable);
  }

  @Override
  public Page<ScheduleAllTheaterMovieDto> getAllScheduleInTheatersByzipcode(String zipcode, ParamFilterSchedule param, Pageable pageable) {
    ScheduleSpecs specs = new ScheduleSpecs(param);
    specs.setZipcode(zipcode);
    specs.setOrdered(true);
    List<Schedule> schedules = scheduleDao.findAll(specs);
    return getAllSchedulePerTheater(schedules, pageable);
  }

  @Override
  public Page<SchedulePerMovieDto> getScheduleByTheaterId(Integer theaterId, ParamFilterSchedule param, Pageable pageable) {
    ScheduleSpecs specs = new ScheduleSpecs(param);
    specs.setTheaterId(theaterId);
    List<Schedule> schedules = scheduleDao.findAll(specs);
    return getSchedulePerMovie(schedules, pageable);
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

  private Page<SchedulePerTheaterDto> getSchedulePerTheater(List<Schedule> schedules, Pageable pageable) {
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

  private Page<SchedulePerMovieDto> getSchedulePerMovie(List<Schedule> schedules, Pageable pageable) {
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

  private Page<ScheduleAllTheaterMovieDto> getAllSchedulePerTheater(List<Schedule> schedules, Pageable pageable) {
    // Note: sorted Schedules only, order by theaterId, movie releaseDate, showtime

    List<ScheduleAllTheaterMovieDto> satmDtos = new ArrayList<>();

    for (Schedule s: schedules) {
      Theater theater = s.getTheater();
      Integer satmNum = satmDtos.size();
      if ( satmNum == 0 || ! satmDtos.get(satmNum - 1).getTheaterId().equals(theater.getTheaterId())){
        satmDtos.add(theaterMapper.toSceduleAllTheaterMovieDto(theater));
        satmNum += 1;
      }
      ScheduleAllTheaterMovieDto satmDto = satmDtos.get(satmNum - 1);
      if ( satmDto.getMovies() == null)
        satmDto.setMovies(new ArrayList<>());

      List<SchedulePerMovieDto> spmDtos = satmDto.getMovies();
      Movie movie = s.getMovie();
      Integer spmNum = spmDtos.size();
      if ( spmNum == 0 || ! spmDtos.get(spmNum - 1).getMovieId().equals(movie.getMovieId())) {
        spmDtos.add(theaterMapper.toPerMovieDto(movie));
        spmNum += 1;
      }
      SchedulePerMovieDto spmDto = spmDtos.get(spmNum - 1);
      if ( spmDto.getSchedules() == null)
        spmDto.setSchedules(new ArrayList<>());

      spmDto.getSchedules().add(scheduleMapper.toScheduleSimpleDto(s));
    }

    return Pagintation.getPage(satmDtos, pageable);
  }
}
