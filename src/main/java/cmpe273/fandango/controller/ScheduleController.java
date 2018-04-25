package cmpe273.fandango.controller;

import cmpe273.fandango.dto.ParamSearchMovie;
import cmpe273.fandango.dto.TheaterScheduleDto;
import cmpe273.fandango.entity.Schedule;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cmpe273.fandango.constant.UrlConstant.SCHEDULES;

@RestController
@Api(tags = {"Schedule"})
@Transactional(rollbackFor = Exception.class)
public class ScheduleController extends AbstractController{

  @Autowired
  private ScheduleService scheduleService;


  @ApiOperation(value = "Get Movies Schedules nearby [Topic: schedules]", response = JsonResponse.class)
  @GetMapping(SCHEDULES+"/{cityId}/{movieId}")
  public ResponseEntity<JsonResponse> getSchedules(@PathVariable Integer cityId, @PathVariable Integer movieId) {
    List<TheaterScheduleDto> schedules = scheduleService.getNearByMovieSchedule(cityId, movieId);
    if ( schedules != null )
      return success("key", schedules);
    return notFound();
  }

}
