package cmpe273.fandango.mapper.impl;

import cmpe273.fandango.dto.ParamCreateSchedule;
import cmpe273.fandango.dto.ParamUpdateSchedule;
import cmpe273.fandango.dto.ScheduleOrderDto;
import cmpe273.fandango.dto.ScheduleSimpleDto;
import cmpe273.fandango.entity.Schedule;
import cmpe273.fandango.mapper.GenericMapper;
import cmpe273.fandango.mapper.ScheduleMapper;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapperImpl extends GenericMapper implements ScheduleMapper {

  public ScheduleSimpleDto toScheduleSimpleDto (Schedule pojo) {

    ScheduleSimpleDto ssDto = mapT1toT2(pojo, new ScheduleSimpleDto());
    ssDto.setFormat(pojo.getFormat().getFormatName());
    return ssDto;
  }

  public ScheduleOrderDto toOrderDto (Schedule pojo) {
    ScheduleOrderDto soDto = mapT1toT2(pojo, new ScheduleOrderDto());
    soDto.setFormat(pojo.getFormat().getFormatName());
    return soDto;
  }

  public Schedule toPojo (ParamCreateSchedule dto){
    return mapT1toT2(dto, new Schedule());
  }

  public Schedule updPojo (ParamUpdateSchedule dto, Schedule pojo){
    if ( dto == null ) return pojo;
    updateValue(pojo::setAvailSeats, dto.getAvailSeats());
    updateValue(pojo::setPrice, dto.getPrice());
    updateValue(pojo::setScheduleDate, dto.getScheduleDate());
    updateValue(pojo::setShowtime, dto.getShowtime());
    updateValue(pojo::setTotSeats, dto.getTotSeats());
    return pojo;
  }

}
