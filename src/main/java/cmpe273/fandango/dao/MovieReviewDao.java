package cmpe273.fandango.dao;

import cmpe273.fandango.entity.MovieReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieReviewDao extends PagingAndSortingRepository<MovieReview, Integer>{
    @Query("select mr from MovieReview mr join mr.movie mv where mr.reviewId = :reviewId")
    MovieReview SelectAllByReviewId(@Param("reviewId") Integer reviewId);

    @Query("select mr from MovieReview mr where mr.movie.movieId = :movieId")
    List<MovieReview> SelectByReviewId(@Param("movieId") Integer movieId);

    @Query("select mr from MovieReview mr join mr.movie mv where mr.movie.movieId = :movieId")
    Page<MovieReview> findAllByPages(Pageable pageable, @Param("movieId") Integer movieId);

}