package cmpe273.fandango.controller;


import cmpe273.fandango.dto.MovieCharacterDto;
import cmpe273.fandango.entity.MovieCharacter;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieCharacterService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE_CHARACTERS;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_CHARACTER;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_CHARACTER_MOVIEID;

@RestController
@Api(tags = {"Movie Characters"})
@Transactional(rollbackFor = Exception.class)
public class MovieCharacterController extends AbstractController {

  @Autowired
  MovieCharacterService movieCharacterService;

  @ApiOperation(value = "Get Characters of a Movie [Topic: movies]", response = JsonResponse.class)
  @GetMapping(MOVIE_CHARACTER_MOVIEID)
  public ResponseEntity<JsonResponse> getMovieCharacters(@PathVariable Integer movieId) {
    List<MovieCharacter> movieCharacters = movieCharacterService.getMovieCharacters(movieId);
    if ( movieCharacters != null )
      return success(KEY_MOVIE_CHARACTERS, movieCharacters);
    return notFound();
  }

  @ApiOperation(value = "Add New Character to Movie [Topic: movies]", response = JsonResponse.class,
    notes = "characterId field is ignored")
  @CacheEvict(value={"get-schedules-by-city-id", "get-all-schedules-by-city-id",
      "get-schedules-by-zipcode", "get-all-schedules-by-zipcode", "get-schedules-in-theater"}, allEntries = true)
  @PostMapping(MOVIE_CHARACTER)
  public ResponseEntity<JsonResponse> addMovieCharacter(@RequestBody MovieCharacterDto movieCharacterDto) {
    if ( movieCharacterDto.getMovieId() == null || movieCharacterDto.getCharacterName() == null)
      return badRequest("Movie ID or Character Name Cannot Be Empty!");
    List<MovieCharacter> movieCharacters = movieCharacterService.addCharacter(movieCharacterDto);
    if (movieCharacters != null )
      return created(KEY_MOVIE_CHARACTERS, movieCharacters);
    else
    return notFound();
  }

  @ApiOperation(value = "Delete a Character from Movie [Topic: movies]", response = JsonResponse.class,
      notes = "characterName field is ignored")
  @CacheEvict(value={"get-schedules-by-city-id", "get-all-schedules-by-city-id",
      "get-schedules-by-zipcode", "get-all-schedules-by-zipcode", "get-schedules-in-theater"}, allEntries = true)
  @DeleteMapping(MOVIE_CHARACTER)
  public ResponseEntity<JsonResponse> removeMovieCharacter(@RequestBody MovieCharacterDto movieCharacterDto) {
    if ( movieCharacterDto.getMovieId() == null || movieCharacterDto.getCharacterId() == null)
      return badRequest("Movie ID or Character ID Cannot Be Empty!");
    List<MovieCharacter> movieCharacters = movieCharacterService.removeCharacter(movieCharacterDto);
    if (movieCharacters != null)
      return success(KEY_MOVIE_CHARACTERS, movieCharacters);
    else
      return notFound();
  }
}
