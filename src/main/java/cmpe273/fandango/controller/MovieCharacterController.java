package cmpe273.fandango.controller;


import cmpe273.fandango.dto.MovieCharacterDto;
import cmpe273.fandango.entity.MovieCharacter;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieCharacterService;
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
import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE_CHARACTER;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_CHARACTER;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_CHARACTER_ID;

@RestController
@Api(tags = {"Movie Characters"})
@SwaggerDefinition(tags = {@Tag(name = "Movie Character Controller", description = "Movie Character Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class MovieCharacterController extends AbstractController {

  @Autowired
  MovieCharacterService movieCharacterService;

  @ApiOperation(value = "Get Characters of a Movie", response = JsonResponse.class)
  @GetMapping(MOVIE_CHARACTER_ID)
  public ResponseEntity<JsonResponse> getMovieCharacters(@PathVariable Integer movieId) {
    List<MovieCharacter> movieCharacters = movieCharacterService.getMovieCharacters(movieId);
    if ( movieCharacters != null )
      return success(KEY_CONTENT, movieCharacters);
    return notFound();
  }

  @ApiOperation(value = "Add New Character to Movie", response = JsonResponse.class,
    notes = "characterId field is ignored")
  @PostMapping(MOVIE_CHARACTER)
  public ResponseEntity<JsonResponse> addMovieCharacter(@RequestBody MovieCharacterDto movieCharacterDto) {
    movieCharacterDto = movieCharacterService.addCharacter(movieCharacterDto);
    if (movieCharacterDto != null )
      return created(KEY_MOVIE_CHARACTER, movieCharacterDto);
    else
    return notFound();
  }

  @ApiOperation(value = "Add New Character to Movie", response = JsonResponse.class,
      notes = "characterName field is ignored")
  @DeleteMapping(MOVIE_CHARACTER)
  public ResponseEntity<JsonResponse> removeMovieCharacter(@RequestBody MovieCharacterDto movieCharacterDto) {
    if (movieCharacterService.removeCharacter(movieCharacterDto))
      return success(KEY_MOVIE_CHARACTER, "deleted");
    else
      return notFound();
  }
}
