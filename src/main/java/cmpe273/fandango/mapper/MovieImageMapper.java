package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieImageDto;
import cmpe273.fandango.entity.MovieImage;

public class MovieImageMapper extends  GenericMapper{

  public MovieImage toPojo (MovieImageDto dto){
    MovieImage pojo = T1toT2(dto, new MovieImage());
    return pojo;
  }

}
