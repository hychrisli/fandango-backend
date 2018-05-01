package cmpe273.fandango.mapper.impl;

import cmpe273.fandango.dto.AggMovieReviewDto;
import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.ParamCreateReview;
import cmpe273.fandango.dto.ParamUpdateReview;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.MovieReview;
import cmpe273.fandango.mapper.GenericMapper;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.mapper.ReviewMapper;
import cmpe273.fandango.mapper.UserMapper;
import cmpe273.fandango.mapper.impl.MovieMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieReviewMapperImpl extends GenericMapper implements ReviewMapper {

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

  @Override
  public AggMovieReviewDto toAggMovieReviewDto(Object row) {

    Object[] fields = (Object[]) row;

    AggMovieReviewDto dto = new AggMovieReviewDto();
    dto.setMovie(movieMapper.toSimpleDto((Movie) fields[0]));
    dto.setReivewNum((Long) fields[1]);
    dto.setMinStars((Integer) fields[2]);
    dto.setMaxStars((Integer) fields[3]);

    return dto;

  }
}
