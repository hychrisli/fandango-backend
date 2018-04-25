package cmpe273.fandango.controller;

import cmpe273.fandango.dto.TheaterDto;
import cmpe273.fandango.dto.TheaterMovieTodayDto;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.ScheduleService;
import cmpe273.fandango.service.TheaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_MESSAGE;
import static cmpe273.fandango.constant.JsonConstant.KEY_THEATER;
import static cmpe273.fandango.constant.JsonConstant.KEY_THEATERS;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Theater"})
@Transactional(rollbackFor = Exception.class)
public class TheaterController extends AbstractController{

  @Autowired
  TheaterService theaterService;

  @Autowired
  ScheduleService scheduleService;


  @ApiOperation(value = "Get Theaters in a city[Topic: theaters]", response = JsonResponse.class)
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
  @GetMapping(THEATERS_CITYID)
  public Page<Theater> getTheaters(@PathVariable Integer cityId, Pageable pageable) {
    return theaterService.getAllTheatersByCityId(cityId, pageable);
  }


  @ApiOperation(value = "Get Theaters and movies today by zipcode[Topic: theaters]", response = JsonResponse.class)
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
  @GetMapping(THEATERS_ZIPCDOE)
  public Page<TheaterMovieTodayDto> getTheatersWithMoviesByZipcode(@PathVariable String zipcode, Pageable pageable) {
    return scheduleService.getTheaterMovieTodayByZipcode(pageable, zipcode);
  }



  @ApiOperation(value = "Add a new theater [Topic: theaters]", response = JsonResponse.class,
      notes = "theaterId field is ignored. All other fields are required")
  @PostMapping(THEATER)
  public ResponseEntity<JsonResponse> createTheater(@Valid @RequestBody TheaterDto theaterDto) {
    Theater theater = theaterService.addTheater(theaterDto);
    if ( theater != null )
      return success(KEY_THEATER, theater);
    return conflict();
  }

  @ApiOperation(value = "Delete a theater [Topic: theaters]", response = JsonResponse.class)
  @DeleteMapping(THEATER_THEATERID)
  public ResponseEntity<JsonResponse> deleteTheater(@PathVariable Integer theaterId) {
    if ( theaterService.removeTheater(theaterId) )
      return success(KEY_MESSAGE, "deleted");
    return badRequest("Delete unsuccessful");
  }



}
