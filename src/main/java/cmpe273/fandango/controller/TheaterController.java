package cmpe273.fandango.controller;

import cmpe273.fandango.dto.ParamCreateTheater;
import cmpe273.fandango.dto.ParamUpdateTheater;
import cmpe273.fandango.dto.TheaterMovieTodayDto;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.exception.AppException;
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

import static cmpe273.fandango.constant.JsonConstant.KEY_MESSAGE;
import static cmpe273.fandango.constant.JsonConstant.KEY_THEATER;
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


  @ApiOperation(value = "Get Theaters and movies today by zipcode [Topic: theaters]", response = JsonResponse.class)
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
  @GetMapping(THEATERS_MOVIES_ZIPCDOE)
  public Page<TheaterMovieTodayDto> getTheatersWithMoviesByZipcode(@PathVariable String zipcode, Pageable pageable) {
    return scheduleService.getTheaterMovieTodayByZipcode(zipcode, pageable);
  }


  @ApiOperation(value = "Get Theaters and movies today by cityId [Topic: theaters]", response = JsonResponse.class)
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
  @GetMapping(THEATERS_MOVIES_CITYID)
  public Page<TheaterMovieTodayDto> getTheatersWithMoviesByCityId(@PathVariable Integer cityId, Pageable pageable) {
    return scheduleService.getTheaterMovieTodayByCityId(cityId, pageable);
  }


  @ApiOperation(value = "Add a new theater [Topic: theaters]", response = JsonResponse.class,
      notes = "theaterId field is ignored. All other fields are required")
  @PostMapping(THEATER)
  public ResponseEntity<JsonResponse> createTheater(@Valid @RequestBody ParamCreateTheater theaterDto) throws AppException {
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

  @ApiOperation(value = "Get a theater [Topic: theaters]", response = JsonResponse.class)
  @GetMapping(THEATER_THEATERID)
  public ResponseEntity<JsonResponse> getTheater(@PathVariable Integer theaterId) {
    Theater theater = theaterService.getTheater(theaterId);
    if ( theater != null )
      return success(KEY_THEATER, theater);
    return notFound();
  }

  @ApiOperation(value = "Update a theater [Topic: theaters]", response = JsonResponse.class)
  @PutMapping(THEATER_THEATERID)
  public ResponseEntity<JsonResponse> updateTheater(@PathVariable Integer theaterId, @RequestBody ParamUpdateTheater param) throws AppException {
    Theater theater = theaterService.updateTheater(theaterId, param);
    if ( theater != null )
      return success(KEY_THEATER, theater);
    return notFound();
  }



}
