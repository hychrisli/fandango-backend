package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.CityService;
import cmpe273.fandango.service.MovieService;
import cmpe273.fandango.service.TheaterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static cmpe273.fandango.constant.UrlConstant.SEARCH_CITIES;
import static cmpe273.fandango.constant.UrlConstant.SEARCH_MOVIES;
import static cmpe273.fandango.constant.UrlConstant.SEARCH_THEATERS;

@RestController
@Api(tags = {"Search"})
@Transactional(rollbackFor = Exception.class)
public class SearchController extends AbstractController {

  @Autowired
  CityService cityService;

  @Autowired
  TheaterService theaterService;

  @Autowired
  MovieService movieService;

  @ApiOperation(value = "Get Cities in a city[Topic: cities]", response = JsonResponse.class)
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
  @GetMapping(SEARCH_CITIES)
  public Page<City> getCities(@PathVariable String pattern, Pageable pageable) {
    return cityService.searchCitiesByPattern(pattern, pageable);
  }

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
  @GetMapping(SEARCH_THEATERS)
  public Page<Theater> getTheaters(@PathVariable String pattern, Pageable pageable) {
    return theaterService.searchTheatersByPattern(pattern, pageable);
  }

  @ApiOperation(value = "Get Movies in a city[Topic: movies]", response = JsonResponse.class)
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
  @GetMapping(SEARCH_MOVIES)
  public Page<MovieSimpleDto> getMovies(@PathVariable String pattern, Pageable pageable) {
    return movieService.searchMoviesByPattern(pattern, pageable);
  }
}
