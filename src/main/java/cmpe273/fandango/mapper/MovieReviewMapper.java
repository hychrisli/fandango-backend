package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.ParamCreateReview;
import cmpe273.fandango.dto.ParamUpdateReview;
import cmpe273.fandango.entity.MovieReview;
import cmpe273.fandango.mapper.impl.MovieMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class MovieReviewMapper extends GenericMapper {

  @Autowired
  MovieMapper movieMapper;

  private UserMapper userMapper = new UserMapper();

  public MovieReviewDto toDto(MovieReview pojo) {
    MovieReviewDto dto = mapT1toT2(pojo, new MovieReviewDto());
    dto.setMovie(movieMapper.toSimpleDto(pojo.getMovie()));
    dto.setUser(userMapper.toSimpleDto(pojo.getUser()));
    return dto;
  }

  public MovieReview toPojo (ParamCreateReview dto){
    return mapT1toT2(dto, new MovieReview());
  }

  public MovieReview updPojo(ParamUpdateReview dto, MovieReview pojo) {
    if (dto == null) {
      return pojo;
    }
    updateValue(pojo::setReviewTitle, dto.getReviewTitle());
    updateValue(pojo::setComment, dto.getComment());
    updateValue(pojo::setStars, dto.getStars());
    return pojo;
  }

}
