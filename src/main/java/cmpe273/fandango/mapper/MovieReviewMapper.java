package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.entity.MovieReview;

public class MovieReviewMapper extends GenericMapper {
    public MovieReviewDto toDto(MovieReview pojo) {
        return T1toT2(pojo, new MovieReviewDto());
    }
}
