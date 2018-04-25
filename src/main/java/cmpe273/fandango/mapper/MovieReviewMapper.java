package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.MovieReviewSimpleDto;
import cmpe273.fandango.entity.MovieReview;

public class MovieReviewMapper extends GenericMapper {
    public MovieReviewDto toDto(MovieReview pojo) {
        return mapT1toT2(pojo, new MovieReviewDto());
    }

    public MovieReviewSimpleDto toSimpleDto(MovieReview pojo) {
        MovieReviewSimpleDto dto = mapT1toT2(pojo, new MovieReviewSimpleDto());
        dto.setMovieId(pojo.getMovie().getMovieId());
        dto.setUserId(pojo.getUser().getUserId());
        return dto;
    }

    public MovieReview updPojo (MovieReviewSimpleDto dto, MovieReview pojo) {
        if (dto == null) {
            return pojo;
        }
        updateValue(pojo::setComment, dto.getComment());
        updateValue(pojo::setStars, dto.getStars());

        return pojo;
    }

}
