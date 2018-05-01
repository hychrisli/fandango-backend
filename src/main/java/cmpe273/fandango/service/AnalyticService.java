package cmpe273.fandango.service;

import cmpe273.fandango.dto.AggMovieReviewDto;
import cmpe273.fandango.dto.RevenueCityDto;
import cmpe273.fandango.dto.RevenueMovieDto;
import cmpe273.fandango.dto.RevenueTheaterDto;
import org.springframework.data.domain.Page;

public interface AnalyticService {

  RevenueMovieDto getMovieRevenue(Integer movieId);

  RevenueTheaterDto getTheaterRevenue(Integer theaterId);

  Page<RevenueMovieDto> getTop10MoviesByRevenue();

  Page<RevenueMovieDto> getTop10MoviesByTickets();

  Page<RevenueTheaterDto> getTop10TheaterSByRevenue();

  Page<RevenueCityDto> getTop10CitiesByRevenue();

  Page<AggMovieReviewDto> getTop10MoviesByReviewNum();

}
