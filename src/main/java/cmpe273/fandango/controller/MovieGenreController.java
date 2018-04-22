package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieGenreService;
import cmpe273.fandango.service.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static cmpe273.fandango.constant.JsonConstant.KEY_CONTENT;
import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE;
import static cmpe273.fandango.constant.UrlConstant.GENRE;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_GENRE;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_GENRE_ID;

@RestController
@Api(tags = {"Movie Genres"})
@SwaggerDefinition(tags = {@Tag(name = "Movie Genre Controller", description = "Movie Genre Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class MovieGenreController extends AbstractController{

  @Autowired
  MovieGenreService movieGenreService;

  @ApiOperation(value = "Get All Genres", response = JsonResponse.class)
  @GetMapping(GENRE)
  public ResponseEntity<JsonResponse> getGenres() {
    return success(KEY_CONTENT, movieGenreService.getAllGenres());
  }


  @ApiOperation(value = "Get All Genres of a Movie", response = JsonResponse.class)
  @GetMapping(MOVIE_GENRE_ID)
  public ResponseEntity<JsonResponse> getMovieGenres(@PathVariable Integer movieId) {
    return success(KEY_CONTENT, movieGenreService.getMovieGenres(movieId));
  }


  @ApiOperation(value="Add Genre", response = JsonResponse.class)
  @PostMapping(MOVIE_GENRE)
  public ResponseEntity<JsonResponse> addGenre(@RequestBody MovieGenreDto movieGenreDto){
    MovieDto movieDto = movieGenreService.addGenre(movieGenreDto);
    if ( movieDto != null)
      return success(KEY_MOVIE, movieDto);
    else
      return notFound();
  }

  @ApiOperation(value="Remove Format", response = JsonResponse.class)
  @DeleteMapping(MOVIE_GENRE)
  public ResponseEntity<JsonResponse> deleteGenre(@RequestBody MovieGenreDto movieGenreDto){
    MovieDto movieDto = movieGenreService.removeGenre(movieGenreDto);
    if ( movieDto != null)
      return success(KEY_MOVIE, movieDto);
    else
      return notFound();
  }
}
