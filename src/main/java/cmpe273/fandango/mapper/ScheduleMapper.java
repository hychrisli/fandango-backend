package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.ParamCreateSchedule;
import cmpe273.fandango.dto.ParamUpdateSchedule;
import cmpe273.fandango.dto.ScheduleSimpleDto;
import cmpe273.fandango.entity.Schedule;

public interface ScheduleMapper {

  ScheduleSimpleDto toScheduleSimpleDto (Schedule pojo);

  Schedule toPojo (ParamCreateSchedule dto);

  Schedule updPojo (ParamUpdateSchedule dto, Schedule pojo);
}
