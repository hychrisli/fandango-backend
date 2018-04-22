package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.entity.Genre;
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

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_CONTENT;
import static cmpe273.fandango.constant.JsonConstant.KEY_GENRES;
import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE;
import static cmpe273.fandango.constant.UrlConstant.GENRE;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_GENRE;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_GENRE_ID;

@RestController
@Api(tags = {"Movie Genres"})
@Transactional(rollbackFor = Exception.class)
public class MovieGenreController extends AbstractController{

  @Autowired
  MovieGenreService movieGenreService;

  @ApiOperation(value = "Get All Genres [Topic: movies]", response = JsonResponse.class)
  @GetMapping(GENRE)
  public ResponseEntity<JsonResponse> getGenres() {
    return success(KEY_CONTENT, movieGenreService.getAllGenres());
  }


  @ApiOperation(value = "Get All Genres of a Movie [Topic: movies]", response = JsonResponse.class)
  @GetMapping(MOVIE_GENRE_ID)
  public ResponseEntity<JsonResponse> getMovieGenres(@PathVariable Integer movieId) {
    return success(KEY_GENRES, movieGenreService.getMovieGenres(movieId));
  }


  @ApiOperation(value="Add Genre [Topic: movies]", response = JsonResponse.class)
  @PostMapping(MOVIE_GENRE)
  public ResponseEntity<JsonResponse> addGenre(@RequestBody MovieGenreDto movieGenreDto){
    List<Genre> genres = movieGenreService.addGenre(movieGenreDto);
    if ( genres != null)
      return success(KEY_GENRES, genres);
    else
      return notFound();
  }

  @ApiOperation(value="Remove Format [Topic: movies]", response = JsonResponse.class)
  @DeleteMapping(MOVIE_GENRE)
  public ResponseEntity<JsonResponse> deleteGenre(@RequestBody MovieGenreDto movieGenreDto){
    List<Genre> genres = movieGenreService.removeGenre(movieGenreDto);
    if ( genres != null)
      return success(KEY_GENRES, genres);
    else
      return notFound();
  }
}
