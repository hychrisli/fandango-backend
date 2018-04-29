package cmpe273.fandango.mapper.impl;

import cmpe273.fandango.dto.OrderDto;
import cmpe273.fandango.dto.ParamCreateOrder;
import cmpe273.fandango.entity.TicketOrder;
import cmpe273.fandango.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Order;

@Component
public class OrderMapperImpl extends GenericMapper implements OrderMapper{

  @Autowired
  MovieMapper movieMapper;

  @Autowired
  TheaterMapper theaterMapper;

  @Autowired
  ScheduleMapper scheduleMapper;

  @Override
  public OrderDto toDto(TicketOrder pojo) {
    OrderDto dto = mapT1toT2(pojo, new OrderDto());
    dto.setMovie(movieMapper.toSimpleDto(pojo.getSchedule().getMovie()));
    dto.setTheater(theaterMapper.toDto(pojo.getSchedule().getTheater()));
    dto.setSchedule(scheduleMapper.toOrderDto(pojo.getSchedule()));

    return dto;
  }

}
