package cmpe273.fandango.controller;

import cmpe273.fandango.dto.*;
import cmpe273.fandango.entity.MovieReview;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.MovieReviewService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import static cmpe273.fandango.constant.JsonConstant.*;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Review"})
@Transactional(rollbackFor = Exception.class)
public class MovieReviewController extends AbstractController {

  @Autowired
  MovieReviewService movieReviewService;

  @ApiOperation(value = "Get a Review [Topic: reviews]", response = JsonResponse.class)
  @GetMapping(MOVIE_REVIEWID)
  public ResponseEntity<JsonResponse> getMovie(@PathVariable Integer reviewId) {

    MovieReviewDto movieReviewDto = movieReviewService.getMovieReviews(reviewId);

    if (movieReviewDto != null)
      return success(KEY_MOVIE_REVIEW, movieReviewDto);
    return notFound();
  }

  @ApiOperation(value = "Add New Review to Movie [Topic: Reviews]", response = JsonResponse.class)
  @PostMapping(MOVIE_REVIEW)
  public ResponseEntity<JsonResponse> addMovieReview(@RequestBody ParamCreateReview dto) {
    MovieReviewDto review = movieReviewService.addReview(dto);
    if (review != null)
      return created(KEY_MOVIE_REVIEW, review);
    else
      return notFound();
  }

  @ApiOperation(value = "Delete a Review [Topic: Reviews]", response = JsonResponse.class)
  @DeleteMapping(MOVIE_REVIEWID)
  public ResponseEntity<JsonResponse> removeMovieReview(@PathVariable Integer reviewId) {

    Boolean isDeleted = movieReviewService.removeReview(reviewId);
    if (isDeleted)
      return success(KEY_MOVIE_REVIEW, "Review Deleted");
    else
      return notFound();
  }

  @ApiOperation(value = "Update a Movie Review [Topic: movies]", response = JsonResponse.class)
  @PutMapping(MOVIE_REVIEW_USER_REVIEWID)
  public ResponseEntity<JsonResponse> updateMovie(@PathVariable Integer userId, @PathVariable Integer reviewId, @RequestBody ParamUpdateReview dto) {
    MovieReviewDto movieReviewDto = movieReviewService.updateReview(userId, reviewId, dto);
    if (movieReviewDto != null)
      return success(KEY_MOVIE_REVIEW, movieReviewDto);
    else
      return notFound();
  }


  @ApiOperation(value = "Get All MovieReviews by movieId or userId [Topic: movieReview]", response = JsonResponse.class)
  @ApiImplicitParams({

      @ApiImplicitParam(name = "movieId", dataType = "integer", paramType = "query",
          value = "movie ID"),
      @ApiImplicitParam(name = "userId", dataType = "integer", paramType = "query",
          value = "userId ID"),
      @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
          value = "Results page you want to retrieve (0..N)"),
      @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
          value = "Number of records per page."),
      @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
          value = "Sorting criteria in the format: property(,asc|desc). " +
              "Default sort order is ascending. " +
              "Multiple sort criteria are supported.")
  })
  @GetMapping(MOVIE_REVIEWS)
  public Page<MovieReviewDto> getMovies(Pageable pageable, Integer movieId, Integer userId) {
    return movieReviewService.pageSearchMovieReview(pageable, movieId, userId);
  }
}
