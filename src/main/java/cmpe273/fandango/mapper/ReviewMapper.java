package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.AggMovieReviewDto;
import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.ParamCreateReview;
import cmpe273.fandango.dto.ParamUpdateReview;
import cmpe273.fandango.entity.MovieReview;

public interface ReviewMapper {

  MovieReviewDto toDto(MovieReview pojo);

  MovieReview toPojo (ParamCreateReview dto);

  MovieReview updPojo(ParamUpdateReview dto, MovieReview pojo);

  AggMovieReviewDto toAggMovieReviewDto(Object row);

}
