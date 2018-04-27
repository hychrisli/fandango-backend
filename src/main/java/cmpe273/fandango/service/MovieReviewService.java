package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.ParamCreateReview;
import cmpe273.fandango.dto.ParamUpdateReview;
import cmpe273.fandango.entity.MovieReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MovieReviewService {
    MovieReviewDto getMovieReviews (Integer movieId);

    MovieReviewDto addReview(ParamCreateReview dto);

    Boolean removeReview(Integer reviewId);

    MovieReviewDto updateReview(Integer userId, Integer reviewId, ParamUpdateReview dto);

    Page<MovieReviewDto> pageSearchMovieReview(Pageable pageable, Integer movieId, Integer userId);
}
