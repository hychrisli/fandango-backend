package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.ScheduleDao;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.dto.ScheduleSimpleDto;
import cmpe273.fandango.dto.TheaterScheduleDto;
import cmpe273.fandango.entity.Schedule;
import cmpe273.fandango.mapper.ScheduleMapper;
import cmpe273.fandango.mapper.TheaterMapper;
import cmpe273.fandango.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

  @Autowired
  ScheduleDao scheduleDao;

  private TheaterMapper theaterMapper = new TheaterMapper();

  private ScheduleMapper scheduleMapper = new ScheduleMapper();

  @Override
  public List<TheaterScheduleDto> getNearByMovieSchedule(Integer cityId, Integer movieId) {
    //long DAY_IN_MS = 1000 * 60 * 60 * 24;
    Date today = new Date();

    Calendar cal = Calendar.getInstance();
    cal.setTime(today);

    cal.set(Calendar.HOUR_OF_DAY, 0);
    cal.set(Calendar.MINUTE, 0);
    cal.set(Calendar.SECOND, 0);
    cal.set(Calendar.MILLISECOND, 0);

    today = cal.getTime();

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
  public List<MovieSimpleDto> searchNearbyAvailableMovie() {
    return null;
  }
}
