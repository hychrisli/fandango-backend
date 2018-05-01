package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.MovieDao;
import cmpe273.fandango.dao.MovieReviewDao;
import cmpe273.fandango.dao.SalesDao;
import cmpe273.fandango.dao.TheaterDao;
import cmpe273.fandango.dto.AggMovieReviewDto;
import cmpe273.fandango.dto.RevenueCityDto;
import cmpe273.fandango.dto.RevenueMovieDto;
import cmpe273.fandango.dto.RevenueTheaterDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.Sales;
import cmpe273.fandango.entity.Theater;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.mapper.ReviewMapper;
import cmpe273.fandango.mapper.SalesMapper;
import cmpe273.fandango.mapper.TheaterMapper;
import cmpe273.fandango.service.AnalyticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AnalyticsServiceImpl implements AnalyticService{

  @Autowired
  SalesDao salesDao;

  @Autowired
  MovieDao movieDao;

  @Autowired
  TheaterDao theaterDao;

  @Autowired
  SalesMapper salesMapper;

  @Autowired
  MovieReviewDao movieReviewDao;

  @Autowired
  ReviewMapper reviewMapper;


  private Pageable pageable = new PageRequest(0, 10);

  @Override
  public RevenueMovieDto getMovieRevenue(Integer movieId) {
    Movie movie = movieDao.findOne(movieId);
    if (movie == null) return null;
    Object row = salesDao.findByMovieId(movieId);
    return salesMapper.toRevMovieDto(movie, row);
  }

  @Override
  public RevenueTheaterDto getTheaterRevenue(Integer theaterId) {
    Theater theater = theaterDao.findOne(theaterId);
    if (theater == null) return null;
    Object row = salesDao.findByTheaterId(theaterId);
    return salesMapper.toRevTheaterDto(theater, row);
  }

  @Override
  public Page<RevenueMovieDto> getTop10MoviesByRevenue() {
    Page<Object> rows = salesDao.findTop10MovieRevenue(pageable);
    return new PageImpl<>(
        StreamSupport.stream(rows.spliterator(), false)
            .map(salesMapper::toRevMovieDto).collect(Collectors.toList()), pageable, 10);
  }

  @Override
  public Page<RevenueMovieDto> getTop10MoviesByTickets() {
    Page<Object> rows = salesDao.findTop10MovieTicketSales(pageable);
    return new PageImpl<>(
        StreamSupport.stream(rows.spliterator(), false)
            .map(salesMapper::toRevMovieDto).collect(Collectors.toList()), pageable, 10);
  }

  @Override
  public Page<RevenueTheaterDto> getTop10TheaterSByRevenue() {
    Page<Object> rows = salesDao.findTop10TheaterRevenue(pageable);
    return new PageImpl<>(
        StreamSupport.stream(rows.spliterator(), false)
            .map(salesMapper::toRevTheaterDto).collect(Collectors.toList()), pageable, 10);
  }

  @Override
  public Page<RevenueCityDto> getTop10CitiesByRevenue() {
    Page<Object> rows =salesDao.findTop10CitiesRevenue(pageable);
    return new PageImpl<>(
        StreamSupport.stream(rows.spliterator(), false)
            .map(salesMapper::toRevCityDto).collect(Collectors.toList()), pageable, 10);
  }

  @Override
  public Page<AggMovieReviewDto> getTop10MoviesByReviewNum() {
    Page<Object> rows = movieReviewDao.findTop10MoviesByReviewNum(pageable);
    return new PageImpl<>(
        StreamSupport.stream(rows.spliterator(), false)
            .map(reviewMapper::toAggMovieReviewDto).collect(Collectors.toList()), pageable, 10);
  }
}
