package cmpe273.fandango.controller;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.dto.MovieGenreDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;
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

import javax.xml.ws.Response;

import java.util.List;

import static cmpe273.fandango.constant.JsonConstant.*;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Review"})
@Transactional(rollbackFor = Exception.class)
public class MovieReviewController extends AbstractController {
    @Autowired
    MovieReviewService movieReviewService;

    @ApiOperation(value = "Get a Review [Topic: review]", response = JsonResponse.class)
    @GetMapping(MOVIE_REVIEW_ID)
    public ResponseEntity<JsonResponse> getMovie(@PathVariable Integer reviewID) {

        MovieReviewDto movieReviewDto = movieReviewService.getMovieReviews(reviewID);

        if (movieReviewDto != null)
            return success(KEY_MOVIE_REVIEW, movieReviewDto);
        return notFound();
    }

    @ApiOperation(value = "Add New Movie to Movie [Topic: Reviews]", response = JsonResponse.class)
    @PostMapping(MOVIE_REVIEW)
    public ResponseEntity<JsonResponse> addMovieReview(@RequestBody MovieReviewDto movieReviewDto) {
        if ( movieReviewDto.getMovieId() == null) {
            return badRequest("Movie ID Cannot Be Empty!");
        }
        List<MovieReview> movieReviews = movieReviewService.addReview(movieReviewDto);
        if (movieReviews != null )
            return created(KEY_MOVIE_REVIEW, movieReviews);
        else
            return notFound();
    }

    @ApiOperation(value = "Delete a Review from Movie", response = JsonResponse.class)
    @DeleteMapping(MOVIE_REVIEW)
    public ResponseEntity<JsonResponse> removeMovieReview(@RequestBody MovieReviewDto movieReviewDto) {
        if ( movieReviewDto.getMovieId() == null || movieReviewDto.getReviewId() == null) {
            return badRequest("Movie ID or Character ID Cannot Be Empty!");
        }
        List<MovieReview> movieReviews = movieReviewService.removeReview(movieReviewDto);
        if (movieReviews != null)
            return success(KEY_MOVIE_REVIEW, movieReviews);
        else
            return notFound();
    }


}
