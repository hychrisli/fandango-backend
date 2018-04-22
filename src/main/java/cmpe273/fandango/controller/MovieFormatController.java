package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieFormatDto;
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

import static cmpe273.fandango.constant.JsonConstant.KEY_CONTENT;
import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE;
import static cmpe273.fandango.constant.UrlConstant.FORMAT;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_FORMAT;


@RestController
@Api(tags = {"Movie Formats"})
@SwaggerDefinition(tags = {@Tag(name = "Movie Format Controller", description = "Movie Format Controller Endpoints")})
@Transactional(rollbackFor = Exception.class)
public class MovieFormatController extends AbstractController{

  @Autowired
  MovieFormatService movieFormatService;


  @ApiOperation(value = "Get All Formats", response = JsonResponse.class)
  @GetMapping(FORMAT)
  public ResponseEntity<JsonResponse> getFormats() {
    return success(KEY_CONTENT, movieFormatService.getAllFormats());
  }


  @ApiOperation(value="Add Format", response = JsonResponse.class)
  @PostMapping(MOVIE_FORMAT)
  public ResponseEntity<JsonResponse> addFormat(@RequestBody MovieFormatDto movieFormatDto){
    MovieDto movieDto = movieFormatService.addFormat(movieFormatDto);
    if ( movieDto != null)
      return success(KEY_MOVIE, movieDto);
    else
      return notFound();
  }

  @ApiOperation(value="Remove Format", response = JsonResponse.class)
  @DeleteMapping(MOVIE_FORMAT)
  public ResponseEntity<JsonResponse> deleteFormat(@RequestBody MovieFormatDto movieFormatDto){
    MovieDto movieDto = movieFormatService.removeFormat(movieFormatDto);
    if ( movieDto != null)
      return success(KEY_MOVIE, movieDto);
    else
      return notFound();
  }

}
