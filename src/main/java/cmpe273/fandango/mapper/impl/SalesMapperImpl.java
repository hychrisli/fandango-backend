package cmpe273.fandango.mapper.impl;

import cmpe273.fandango.dto.RevenueMovieDto;
import cmpe273.fandango.dto.RevenueTheaterDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Revenue;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.mapper.SalesMapper;
import cmpe273.fandango.mapper.TheaterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SalesMapperImpl implements SalesMapper{

  @Autowired
  MovieMapper movieMapper;

  @Autowired
  TheaterMapper theaterMapper;



  @Override
  public RevenueTheaterDto toRevTheaterDto(Object row) {
    Object[] fields = (Object[]) row;
    return toRevTheaterDto((Theater) fields[0], (Double)fields[1], (Long)fields[2]);
  }

  @Override
  public RevenueTheaterDto toRevTheaterDto(Theater theater, Object row) {
    Object[] fields = (Object[]) row;
    return toRevTheaterDto(theater, (Double)fields[0], (Long)fields[1]);
  }

  @Override
  public RevenueMovieDto toRevMovieDto(Object row) {
    Object[] fields = (Object[]) row;
    return toRevMovieDto((Movie)fields[0], (Double) fields[1], (Long)fields[2]);
  }

  @Override
  public RevenueMovieDto toRevMovieDto(Movie movie, Object row) {
    Object[] fields = (Object[]) row;
    return toRevMovieDto(movie, (Double) fields[0], (Long) fields[1]);
  }


  private RevenueMovieDto toRevMovieDto(Movie movie, Double revenue, Long tickets) {
    RevenueMovieDto dto = new RevenueMovieDto();
    dto.setMovie(movieMapper.toSimpleDto(movie));
    if (revenue != null )
      dto.setRevenue(revenue);
    if (tickets != null)
      dto.setTickets(tickets);
    return dto;
  }

  private RevenueTheaterDto toRevTheaterDto(Theater theater, Double revenue, Long tickets){

    RevenueTheaterDto dto = new RevenueTheaterDto();
    dto.setTheater(theaterMapper.toDto(theater));

    if (revenue != null)
      dto.setRevenue(revenue);
    if (tickets != null)
      dto.setTickets(tickets);
    return dto;
  }
}
