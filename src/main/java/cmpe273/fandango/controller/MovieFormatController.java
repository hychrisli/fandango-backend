package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.entity.Format;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieFormatService;
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
import static cmpe273.fandango.constant.JsonConstant.KEY_FORMATS;
import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE;
import static cmpe273.fandango.constant.UrlConstant.FORMAT;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_FORMAT;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_FORMAT_ID;


@RestController
@Api(tags = {"Movie Formats"})
@Transactional(rollbackFor = Exception.class)
public class MovieFormatController extends AbstractController{

  @Autowired
  MovieFormatService movieFormatService;


  @ApiOperation(value = "Get All Formats [Topic: movies]", response = JsonResponse.class)
  @GetMapping(FORMAT)
  public ResponseEntity<JsonResponse> getFormats() {
    return success(KEY_CONTENT, movieFormatService.getAllFormats());
  }

  @ApiOperation(value = "Get All Formats of a Movie [Topic: movies]", response = JsonResponse.class)
  @GetMapping(MOVIE_FORMAT_ID)
  public ResponseEntity<JsonResponse> getMovieFormats(@PathVariable Integer movieId) {
    return success(KEY_FORMATS, movieFormatService.getMovieFormats(movieId));
  }

  @ApiOperation(value="Add Format [Topic: movies]", response = JsonResponse.class)
  @PostMapping(MOVIE_FORMAT)
  public ResponseEntity<JsonResponse> addFormat(@RequestBody MovieFormatDto movieFormatDto){
    List<Format> formats = movieFormatService.addFormat(movieFormatDto);
    if ( formats != null)
      return success(KEY_FORMATS, formats);
    else
      return notFound();
  }

  @ApiOperation(value="Remove Format [Topic: movies]", response = JsonResponse.class)
  @DeleteMapping(MOVIE_FORMAT)
  public ResponseEntity<JsonResponse> deleteFormat(@RequestBody MovieFormatDto movieFormatDto){
    List<Format> formats = movieFormatService.removeFormat(movieFormatDto);
    if ( formats != null)
      return success(KEY_FORMATS, formats);
    else
      return notFound();
  }

}
