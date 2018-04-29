package cmpe273.fandango.controller;

import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.Schedule;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_SCHEDULE;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Schedule"})
@Transactional(rollbackFor = Exception.class)
public class ScheduleController extends AbstractController{

  @Autowired
  private ScheduleService scheduleService;


  @ApiOperation(value = "Get Schedule [Topic: schedules]", response = JsonResponse.class)
  @GetMapping(SCHEDULE_ID)
  public ResponseEntity<JsonResponse> getScheduleById(@PathVariable Long scheduleId) {

    Schedule schedule = scheduleService.getScheduleById(scheduleId);
    if (schedule != null)
      return success(KEY_SCHEDULE, schedule);
    return notFound();
  }


  @ApiOperation(value = "Post Schedule [Topic: schedules]", response = JsonResponse.class)
  @PostMapping(SCHEDULE)
  public ResponseEntity<JsonResponse> createSchedule(@RequestBody ParamCreateSchedule param) {
    Schedule schedule = scheduleService.createSchedule(param);
    if (schedule != null)
      return created(KEY_SCHEDULE, schedule);
    return badRequest("Invalid Request Body");
  }

  @ApiOperation(value = "Update Schedule [Topic: schedules]", response = JsonResponse.class)
  @PutMapping(SCHEDULE_ID)
  public ResponseEntity<JsonResponse> updateSchedule(@PathVariable Long scheduleId, @RequestBody ParamUpdateSchedule param) {
    Schedule schedule = scheduleService.updateSchedule(scheduleId, param);
    if (schedule != null)
      return success(KEY_SCHEDULE, schedule);
    return notFound();
  }

  @ApiOperation(value = "Update Schedule [Topic: schedules]", response = JsonResponse.class)
  @DeleteMapping(SCHEDULE_ID)
  public ResponseEntity<JsonResponse> deleteSchedule(@PathVariable Long scheduleId) {
    if (scheduleService.deleteSchedule(scheduleId))
      return success(KEY_SCHEDULE, "deleted");
    return notFound();
  }


  @ApiOperation(value = "Get Nearby Movie Schedules by cityId [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "minPrice", dataType = "float", paramType = "query",
          value = "Minimum movie ticket price"),
      @ApiImplicitParam(name = "maxPrice", dataType = "float", paramType = "query",
          value = "Maximum movie ticket price"),
      @ApiImplicitParam(name = "minStars", dataType = "float", paramType = "query",
          value = "Minimum movie stars"),
      @ApiImplicitParam(name = "maxStars", dataType = "float", paramType = "query",
          value = "Maximum movie stars"),
      @ApiImplicitParam(name = "scheduleDate", dataType = "date", paramType = "query",
          value = "Movie schedule date, format yyyy-MM-dd, default today"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(SCHEDULES_CITYID_MOVIEID)
  public Page<SchedulePerTheaterDto> getSchedulesByCityId(
      @PathVariable Integer cityId,
      @PathVariable Integer movieId,
      ParamFilterSchedule param,
      Pageable pageable) {
    return scheduleService.getMovieScheduleInTheatersByCityId(cityId, movieId,param, pageable);
  }

  @ApiOperation(value = "Get All Movie Schedules by cityId [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "minPrice", dataType = "float", paramType = "query",
          value = "Minimum movie ticket price"),
      @ApiImplicitParam(name = "maxPrice", dataType = "float", paramType = "query",
          value = "Maximum movie ticket price"),
      @ApiImplicitParam(name = "minStars", dataType = "float", paramType = "query",
          value = "Minimum movie stars"),
      @ApiImplicitParam(name = "maxStars", dataType = "float", paramType = "query",
          value = "Maximum movie stars"),
      @ApiImplicitParam(name = "scheduleDate", dataType = "date", paramType = "query",
          value = "Movie schedule date, format yyyy-MM-dd, default today"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(SCHEDULES_CITYID)
  public Page<ScheduleAllTheaterMovieDto> getAllSchedulesByCityId(
      @PathVariable Integer cityId,
      ParamFilterSchedule param,
      Pageable pageable) {
    return scheduleService.getAllScheduleInTheatersByCityId(cityId, param, pageable);
  }


  @ApiOperation(value = "Get Nearby Movie Schedules by zipcode [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "minPrice", dataType = "float", paramType = "query",
          value = "Minimum movie ticket price"),
      @ApiImplicitParam(name = "maxPrice", dataType = "float", paramType = "query",
          value = "Maximum movie ticket price"),
      @ApiImplicitParam(name = "minStars", dataType = "float", paramType = "query",
          value = "Minimum movie stars"),
      @ApiImplicitParam(name = "maxStars", dataType = "float", paramType = "query",
          value = "Maximum movie stars"),
      @ApiImplicitParam(name = "scheduleDate", dataType = "date", paramType = "query",
          value = "Movie schedule date, format yyyy-MM-dd, default today"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(SCHEDULES_ZIPCODE_MOVIEID)
  public Page<SchedulePerTheaterDto> getSchedulesByZipcode(
      @PathVariable String zipcode,
      @PathVariable Integer movieId,
      ParamFilterSchedule param,
      Pageable pageable) {
    return scheduleService.getMovieScheduleInTheatersByzipcode(zipcode, movieId,param, pageable);
  }

  @ApiOperation(value = "Get Nearby Movie Schedules by zipcode [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "minPrice", dataType = "float", paramType = "query",
          value = "Minimum movie ticket price"),
      @ApiImplicitParam(name = "maxPrice", dataType = "float", paramType = "query",
          value = "Maximum movie ticket price"),
      @ApiImplicitParam(name = "minStars", dataType = "float", paramType = "query",
          value = "Minimum movie stars"),
      @ApiImplicitParam(name = "maxStars", dataType = "float", paramType = "query",
          value = "Maximum movie stars"),
      @ApiImplicitParam(name = "scheduleDate", dataType = "date", paramType = "query",
          value = "Movie schedule date, format yyyy-MM-dd, default today"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(SCHEDULES_ZIPCODE)
  public Page<ScheduleAllTheaterMovieDto> getAllSchedulesByZipcode(
      @PathVariable String zipcode,
      ParamFilterSchedule param,
      Pageable pageable) {
    return scheduleService.getAllScheduleInTheatersByzipcode(zipcode, param, pageable);
  }


  @ApiOperation(value = "Get Movie Schedules of a Theater [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "minPrice", dataType = "float", paramType = "query",
          value = "Minimum movie ticket price"),
      @ApiImplicitParam(name = "maxPrice", dataType = "float", paramType = "query",
          value = "Maximum movie ticket price"),
      @ApiImplicitParam(name = "minStars", dataType = "float", paramType = "query",
          value = "Minimum movie stars"),
      @ApiImplicitParam(name = "maxStars", dataType = "float", paramType = "query",
          value = "Maximum movie stars"),
      @ApiImplicitParam(name = "scheduleDate", dataType = "date", paramType = "query",
          value = "Movie schedule date, format yyyy-MM-dd, default today"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(SCHEDULES_THEATER)
  public Page<SchedulePerMovieDto> getSchedulesInTheater(
      @PathVariable Integer theaterId,
      ParamFilterSchedule param,
      Pageable pageable) {
    return scheduleService.getScheduleByTheaterId(theaterId, param, pageable);
  }

}
