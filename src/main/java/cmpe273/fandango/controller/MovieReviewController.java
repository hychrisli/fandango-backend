package cmpe273.fandango.controller;

import cmpe273.fandango.dto.*;
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
    public ResponseEntity<JsonResponse> addMovieReview(@RequestBody MovieReviewSimpleDto movieReviewDto) {
        if ( movieReviewDto.getMovieId() == null) {
            return badRequest("Movie ID Cannot Be Empty!");
        }
        ///List<MovieReview> movieReviews = movieReviewService.addReview(movieReviewDto);
        MovieReview movieReviews = movieReviewService.addReview(movieReviewDto);
        if (movieReviews != null )
            return created(KEY_MOVIE_REVIEW, movieReviews);
        else
            return notFound();
    }

    @ApiOperation(value = "Delete a Review from Movie", response = JsonResponse.class)
    @DeleteMapping(MOVIE_REVIEW_ID)
    public ResponseEntity<JsonResponse> removeMovieReview(@PathVariable Integer reviewID) {

        boolean result = movieReviewService.removeReview(reviewID);
        if (result == true)
            return success(KEY_MOVIE_REVIEW, "Review Deleted");
        else
            return notFound();
    }

    @ApiOperation(value="Update a Movie Review [Topic: movies]", response = JsonResponse.class)
    @PutMapping(MOVIE_REVIEW_ID)
    public ResponseEntity<JsonResponse> updateMovie(@PathVariable Integer reviewID, @RequestBody MovieReviewSimpleDto movieReviewDto){
        movieReviewDto = movieReviewService.updateReview(reviewID, movieReviewDto);
        if ( movieReviewDto != null)
            return success(KEY_MOVIE_REVIEW, movieReviewDto);
        else
            return notFound();
    }

    @ApiOperation(value = "Get All MovieReviews by movieId [Topic: movieReview]", response = JsonResponse.class)
    @ApiImplicitParams({

            @ApiImplicitParam(name = "movieId", dataType = "integer", paramType = "query",
                    value = "movie Id"),
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
    public List<MovieReviewDto> getMovies(Pageable pageable, Integer movieId) {
        return movieReviewService.pageSearchMovieReview(pageable, movieId);
    }

    @ApiOperation(value = "Get All MovieReviews by userId [Topic: movieReview]", response = JsonResponse.class)
    @ApiImplicitParams({

            @ApiImplicitParam(name = "userId", dataType = "integer", paramType = "query",
                    value = "user Id"),
            @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query",
                    value = "Results page you want to retrieve (0..N)"),
            @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query",
                    value = "Number of records per page."),
            @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
                    value = "Sorting criteria in the format: property(,asc|desc). " +
                            "Default sort order is ascending. " +
                            "Multiple sort criteria are supported.")
    })
    @GetMapping(MOVIE_REVIEW_USER)
    public List<MovieReviewDto> SearchReviewByUserId(Pageable pageable, Integer userId) {
        return movieReviewService.pageSearchMovieReviewUserId(pageable, userId);
    }

}
