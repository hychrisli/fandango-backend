package cmpe273.fandango.controller;


import cmpe273.fandango.dto.AggMovieReviewDto;
import cmpe273.fandango.dto.RevenueCityDto;
import cmpe273.fandango.dto.RevenueMovieDto;
import cmpe273.fandango.dto.RevenueTheaterDto;
import cmpe273.fandango.entity.City;
import cmpe273.fandango.response.JsonResponse;
import cmpe273.fandango.service.AnalyticService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIE;
import static cmpe273.fandango.constant.JsonConstant.KEY_MOVIES;
import static cmpe273.fandango.constant.JsonConstant.KEY_THEATER;
import static cmpe273.fandango.constant.UrlConstant.*;

@RestController
@Api(tags = {"Analytics"})
@Transactional(rollbackFor = Exception.class)
public class AnalyticController extends AbstractController {

  @Autowired
  AnalyticService analyticService;

  @ApiOperation(value = "Get Movie Revenue [Topic: analytics]", response = JsonResponse.class)
  @GetMapping(ANALYTICS_MOVIE_REVENUE_ID)
  public ResponseEntity<JsonResponse> getMovieRevenue(@PathVariable Integer movieId) {
    RevenueMovieDto dto = analyticService.getMovieRevenue(movieId);
    if ( dto != null )
      return success(KEY_MOVIE, dto);
    return notFound();
  }

  @ApiOperation(value = "Get Theater Revenue [Topic: analytics]", response = JsonResponse.class)
  @GetMapping(ANALYTICS_THEATER_REVENUE_ID)
  public ResponseEntity<JsonResponse> getTheaterRevenue(@PathVariable Integer theaterId) {
    RevenueTheaterDto dto = analyticService.getTheaterRevenue(theaterId);
    if ( dto != null )
      return success(KEY_THEATER, dto);
    return notFound();
  }

  @ApiOperation(value = "Get Top 10 Movie By Revenue [Topic: analytics]", response = JsonResponse.class)
  @GetMapping(ANALYTICS_TOP10_MOVIE_REVENUE)
  public Page<RevenueMovieDto> getTop10MoviesByRevenue() {
    return analyticService.getTop10MoviesByRevenue();
  }

  @ApiOperation(value = "Get Top 10 Movie By Ticket Sales [Topic: analytics]", response = JsonResponse.class)
  @GetMapping(ANALYTICS_TOP10_MOVIE_TICKET_SALES)
  public Page<RevenueMovieDto> getTop10MoviesByTicketSales() {
    return analyticService.getTop10MoviesByTickets();
  }

  @ApiOperation(value = "Get Top 10 Cities By Revenue [Topic: analytics]", response = JsonResponse.class)
  @GetMapping(ANALYTICS_TOP10_CITY_REVENUE)
  public Page<RevenueCityDto> getTop10CitiesByTicketSales() {
    return analyticService.getTop10CitiesByRevenue();
  }

  @ApiOperation(value = "Get Top 10 Movies By # of Reviews [Topic: analytics]", response = JsonResponse.class)
  @GetMapping(ANALYTICS_TOP10_MOVIE_REVIEWS)
  public Page<AggMovieReviewDto> getTop10MoviewByReviewNum() {
    return analyticService.getTop10MoviesByReviewNum();
  }


}
