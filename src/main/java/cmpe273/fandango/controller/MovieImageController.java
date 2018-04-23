package cmpe273.fandango.controller;


import cmpe273.fandango.dto.MovieImageDto;
import cmpe273.fandango.entity.MovieImage;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE_IMAGES;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_IMAGE;
import static cmpe273.fandango.constant.UrlConstant.MOVIE_IMAGE_MOVIEID;

@RestController
@Api(tags = {"Movie Images"})
@Transactional(rollbackFor = Exception.class)
public class MovieImageController  extends AbstractController{

  @Autowired
  MovieImageService movieImageService;

  @ApiOperation(value = "Get Images of a Movie [Topic: movies]", response = JsonResponse.class)
  @GetMapping(MOVIE_IMAGE_MOVIEID)
  public ResponseEntity<JsonResponse> getMovieCharacters(@PathVariable Integer movieId) {
    List<MovieImage> movieImages = movieImageService.getAllMovieImages(movieId);
    if ( movieImages != null )
      return success(KEY_MOVIE_IMAGES, movieImages);
    return notFound();
  }

  @ApiOperation(value = "Add a New Image to Movie [Topic: movies]", response = JsonResponse.class,
      notes = "imageId field is ignored")
  @PostMapping(MOVIE_IMAGE)
  public ResponseEntity<JsonResponse> addMovieCharacter(@RequestBody MovieImageDto movieImageDto) {
    if ( movieImageDto.getMovieId() == null || movieImageDto.getImageUrl() == null)
      return badRequest("Movie ID or Image Url Name Cannot Be Empty!");
    List<MovieImage> movieImages = movieImageService.addMovieImage(movieImageDto);
    if (movieImages != null )
      return created(KEY_MOVIE_IMAGES, movieImages);
    else
      return notFound();
  }

  @ApiOperation(value = "Delete an Image from Movie [Topic: movies]", response = JsonResponse.class,
      notes = "imageTitle, imageUrl, and imageDesc fields are ignored")
  @DeleteMapping(MOVIE_IMAGE)
  public ResponseEntity<JsonResponse> removeMovieCharacter(@RequestBody MovieImageDto movieImageDto) {
    if ( movieImageDto.getMovieId() == null || movieImageDto.getImageId() == null)
      return badRequest("Movie ID or Character ID Cannot Be Empty!");
    List<MovieImage> movieImages = movieImageService.removeMovieImage(movieImageDto);
    if (movieImages != null )
      return created(KEY_MOVIE_IMAGES, movieImages);
    else
      return notFound();
  }


}
