package cmpe273.fandango.controller;

import cmpe273.fandango.dto.SchedulePerMovieDto;
import cmpe273.fandango.dto.SchedulePerTheaterDto;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.ScheduleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Schedule"})
@Transactional(rollbackFor = Exception.class)
public class ScheduleController extends AbstractController{

  @Autowired
  private ScheduleService scheduleService;


  @ApiOperation(value = "Get Nearby Movie Schedules by cityId [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
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
  public Page<SchedulePerTheaterDto> getSchedulesByCityId(@PathVariable Integer cityId, @PathVariable Integer movieId, Pageable pageable) {
    return scheduleService.getScheduleInTheatersByCityId(cityId, movieId, pageable);
  }


  @ApiOperation(value = "Get Nearby Movie Schedules by zipcode [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
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
  public Page<SchedulePerTheaterDto> getSchedulesByZipcode(@PathVariable String zipcode, @PathVariable Integer movieId, Pageable pageable) {
    return scheduleService.getScheduleInTheatersByzipcode(zipcode, movieId, pageable);
  }


  @ApiOperation(value = "Get Movie Schedules of a Theater [Topic: schedules]", response = JsonResponse.class)
  @ApiImplicitParams({
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
  public Page<SchedulePerMovieDto> getSchedulesInTheater(@PathVariable Integer theaterId, Pageable pageable) {
    return scheduleService.getScheduleByTheaterId(theaterId, pageable);
  }

}
