package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.entity.Format;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieFormatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_CONTENT;
import static cmpe273.fandango.constant.JsonConstant.KEY_FORMATS;
import static cmpe273.fandango.constant.UrlConstant.*;


@RestController
@Api(tags = {"Movie Formats"})
@Transactional(rollbackFor = Exception.class)
public class MovieFormatController extends AbstractController{

  @Autowired
  MovieFormatService movieFormatService;


  @ApiOperation(value = "Get All Formats [Topic: movies]", response = JsonResponse.class)
  @GetMapping(FORMATS)
  public ResponseEntity<JsonResponse> getFormats() {
    return success(KEY_CONTENT, movieFormatService.getAllFormats());
  }

  @ApiOperation(value = "Get All Formats of a Movie [Topic: movies]", response = JsonResponse.class)
  @GetMapping(MOVIE_FORMAT_MOVIEID)
  public ResponseEntity<JsonResponse> getMovieFormats(@PathVariable Integer movieId) {
    return success(KEY_FORMATS, movieFormatService.getMovieFormats(movieId));
  }

  @ApiOperation(value="Add Format [Topic: movies]", response = JsonResponse.class)
  @PostMapping(MOVIE_FORMAT)
  @CacheEvict(value={"get-schedules-by-city-id", "get-all-schedules-by-city-id",
      "get-schedules-by-zipcode", "get-all-schedules-by-zipcode", "get-schedules-in-theater"}, allEntries = true)
  public ResponseEntity<JsonResponse> addFormat(@RequestBody MovieFormatDto movieFormatDto){
    if ( movieFormatDto.getMovieId() == null || movieFormatDto.getFormatId() == null)
      return badRequest("Movie ID or Format ID Cannot Be null");

    List<Format> formats = movieFormatService.addFormat(movieFormatDto);
    if ( formats != null)
      return success(KEY_FORMATS, formats);
    else
      return notFound();
  }

  @ApiOperation(value="Remove Format [Topic: movies]", response = JsonResponse.class)
  @CacheEvict(value={"get-schedules-by-city-id", "get-all-schedules-by-city-id",
      "get-schedules-by-zipcode", "get-all-schedules-by-zipcode", "get-schedules-in-theater"}, allEntries = true)
  @DeleteMapping(MOVIE_FORMAT)
  public ResponseEntity<JsonResponse> deleteFormat(@RequestBody MovieFormatDto movieFormatDto){
    if ( movieFormatDto.getMovieId() == null || movieFormatDto.getFormatId() == null)
      return badRequest("Movie ID or Format ID Cannot Be null");

    List<Format> formats = movieFormatService.removeFormat(movieFormatDto);
    if ( formats != null)
      return success(KEY_FORMATS, formats);
    else
      return notFound();
  }

}
