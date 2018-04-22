package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieSimpleDto;
import cmpe273.fandango.entity.Movie;

public class MovieMapper extends GenericMapper {

  public MovieDto toDto(Movie pojo) {
    return T1toT2(pojo, new MovieDto());
  }

  public MovieSimpleDto toSimpleDto(Movie pojo){
    return T1toT2(pojo, new MovieSimpleDto());
  }

  public Movie toPojo(MovieDto dto) {
    return T1toT2(dto, new Movie());
  }

  public Movie toPojo(MovieSimpleDto dto){
    if (dto == null) return null;
    return T1toT2(dto, new Movie());
  }

  public Movie updPojo (MovieSimpleDto dto, Movie pojo) {
    if ( dto == null ) return pojo;
    updateValue(pojo::setCoverImageUrl, dto.getCoverImageUrl());
    updateValue(pojo::setLength, dto.getLength());
    updateValue(pojo::setMovieDesc, dto.getMovieDesc());
    updateValue(pojo::setMovieTitle, dto.getMovieTitle());
    updateValue(pojo::setReleaseDate, dto.getReleaseDate());
    updateValue(pojo::setTrailerUrl, dto.getTrailerUrl());
    return pojo;
  }

}
