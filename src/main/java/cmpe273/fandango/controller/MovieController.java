package cmpe273.fandango.controller;


import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Movie"})
@SwaggerDefinition(tags = {@Tag(name = "Movie Controller", description = "Movie Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class MovieController extends  AbstractController{

  @Autowired
  MovieService movieService;

  @ApiOperation(value = "Get All Movies", response = JsonResponse.class)
  @ApiImplicitParams({
      @ApiImplicitParam(name = "minStars", dataType = "integer", paramType = "query",
          value = "Min stars"),
      @ApiImplicitParam(name = "maxStars", dataType = "integer", paramType = "query",
          value = "Max Stars"),
      @ApiImplicitParam(name = "genreId", dataType = "integer", paramType = "query",
          value = "genre Id"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(MOVIES)
  public Page<MovieSimpleDto> getMovies(Pageable pageable, Float minStars, Float maxStars, Integer genreId) {
    return movieService.getAllMovies(pageable, minStars, maxStars, genreId);
  }


  @ApiOperation(value = "Get a Movie", response = JsonResponse.class)
  @GetMapping(MOVIE_ID)
  public ResponseEntity<JsonResponse> getMovie(@PathVariable Integer movieId) {

    MovieDto movieDto = movieService.getMovie(movieId);

    if (movieDto != null)
      return success("movie", movieDto);
    return notFound();
  }


}
