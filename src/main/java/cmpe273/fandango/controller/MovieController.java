package cmpe273.fandango.controller;


import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Movie"})
@Transactional(rollbackFor = Exception.class)
public class MovieController extends  AbstractController{

  @Autowired
  MovieService movieService;

  @ApiOperation(value = "Get All Movies [Topic: movies]", response = JsonResponse.class)
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

  @ApiOperation(value = "Get a Movie [Topic: movies]", response = JsonResponse.class)
  @GetMapping(MOVIE_ID)
  public ResponseEntity<JsonResponse> getMovie(@PathVariable Integer movieId) {

    MovieDto movieDto = movieService.getMovie(movieId);

    if (movieDto != null)
      return success(KEY_MOVIE, movieDto);
    return notFound();
  }

  @ApiOperation(value="Create a Movie [Topic: movies]", response = JsonResponse.class,
      notes = "movieId, mpaaRating and stars in request body are ignored")
  @PostMapping(MOVIE)
  public ResponseEntity<JsonResponse> createMovie(@RequestBody MovieSimpleDto movieSimpleDto){

    if ( movieSimpleDto.getMovieTitle() == null )
      return badRequest("Movie Title Cannot be Empty");

    movieSimpleDto = movieService.CreateMovie(movieSimpleDto);

    if ( movieSimpleDto != null)
      return created(KEY_MOVIE, movieSimpleDto);
    else
      return badRequest("Failed to Create Movie");
  }

  @ApiOperation(value="Update a Movie [Topic: movies]", response = JsonResponse.class,
      notes = "movieId, mpaaRating and stars in request body are ignored")
  @PutMapping(MOVIE_ID)
  public ResponseEntity<JsonResponse> updateMovie(@PathVariable Integer movieId, @RequestBody MovieSimpleDto movieSimpleDto){
    movieSimpleDto = movieService.UpdateMovie(movieId, movieSimpleDto);
    if ( movieSimpleDto != null)
      return success(KEY_MOVIE, movieSimpleDto);
    else
      return notFound();
  }

}
