package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.ScheduleSimpleDto;
import cmpe273.fandango.entity.Schedule;

public class ScheduleMapper extends GenericMapper{

  public ScheduleSimpleDto toScheduleSimpleDto (Schedule pojo) {

    ScheduleSimpleDto ssDto = mapT1toT2(pojo, new ScheduleSimpleDto());
    ssDto.setFormat(pojo.getFormat().getFormatName());
    return ssDto;
  }

}
