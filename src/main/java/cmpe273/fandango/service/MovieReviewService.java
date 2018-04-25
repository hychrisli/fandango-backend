package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.MovieReviewSimpleDto;
import cmpe273.fandango.entity.MovieReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MovieReviewService {
    MovieReviewDto getMovieReviews (Integer movieId);

    MovieReview addReview(MovieReviewSimpleDto movieReviewDto);

    boolean removeReview(Integer reviewId);

    MovieReviewSimpleDto updateReview(Integer reviewId, MovieReviewSimpleDto dto);

    Page<MovieReviewDto> pageSearchMovieReview(Pageable pageable, Integer movieId, Integer userId);
}
