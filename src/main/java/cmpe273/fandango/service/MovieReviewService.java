package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.entity.MovieReview;

import java.util.List;

public interface MovieReviewService {
    MovieReviewDto getMovieReviews (Integer movieId);

    List<MovieReview> addReview(MovieReviewDto movieReviewDto);

    List<MovieReview> removeReview(MovieReviewDto movieReviewDto);


}
