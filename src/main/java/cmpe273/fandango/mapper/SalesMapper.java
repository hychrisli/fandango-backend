package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.RevenueCityDto;
import cmpe273.fandango.dto.RevenueMovieDto;
import cmpe273.fandango.dto.RevenueTheaterDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Theater;

public interface SalesMapper {

  RevenueTheaterDto toRevTheaterDto(Object row);

  RevenueTheaterDto toRevTheaterDto(Theater theater, Object row);

  RevenueMovieDto toRevMovieDto(Object row);

  RevenueMovieDto toRevMovieDto(Movie movie, Object row);

  RevenueCityDto toRevCityDto(Object row);

}
