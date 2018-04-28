package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.dto.ParamCreateReview;
import cmpe273.fandango.dto.ParamUpdateReview;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.mapper.MovieMapper;
import cmpe273.fandango.mapper.impl.MovieMapperImpl;
import cmpe273.fandango.mapper.UserMapper;
import cmpe273.fandango.service.MovieReviewService;
import cmpe273.fandango.dao.MovieReviewDao;
import cmpe273.fandango.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import cmpe273.fandango.mapper.MovieReviewMapper;
import cmpe273.fandango.dao.MovieDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {
  @Autowired
  MovieReviewDao movieReviewDao;

  @Autowired
  MovieDao movieDao;

  @Autowired
  UserDao userDao;

  @Autowired
  MovieMapper movieMapper;

  private MovieReviewMapper movieReviewMapper = new MovieReviewMapper();

  private UserMapper userMapper = new UserMapper();

  @Override
  public MovieReviewDto getMovieReviews(Integer reviewId) {
    MovieReview movieReview = movieReviewDao.findOne(reviewId);
    if (movieReview != null) {
      MovieReviewDto movieReviewDto = movieReviewMapper.toDto(movieReview);
      movieReviewDto.setMovie(movieMapper.toSimpleDto(movieReview.getMovie()));
      movieReviewDto.setUser(userMapper.toSimpleDto(movieReview.getUser()));
      return movieReviewDto;
    }
    return null;
  }

  public MovieReviewDto addReview(ParamCreateReview dto) {
    Movie movie = movieDao.findOne(dto.getMovieId());
    User user = userDao.findOne(dto.getUserId());
    if (movie == null || user == null) return null;

    MovieReview movieReview = movieReviewMapper.toPojo(dto);
    movieReview.setMovie(movie);
    movieReview.setUser(user);
    movieReview.setPostDate(new Date());
    return movieReviewMapper.toDto(movieReviewDao.save(movieReview));

  }

  public Boolean removeReview(Integer reviewId) {
    MovieReview movieReview = movieReviewDao.findOne(reviewId);
    if (movieReview == null) return false;
    movieReviewDao.delete(reviewId);
    return true;
  }

  @Override
  public MovieReviewDto updateReview(Integer userId, Integer reviewId, ParamUpdateReview dto) {
    MovieReview movieReview = movieReviewDao.findOne(reviewId);
    if (movieReview == null || ! movieReview.getUser().getUserId().equals(userId)) return null;
    movieReview = movieReviewMapper.updPojo(dto, movieReview);
    return movieReviewMapper.toDto(movieReviewDao.save(movieReview));
  }

  @Override
  public Page<MovieReviewDto> pageSearchMovieReview(Pageable pageable, Integer movieId, Integer userId) {

    Page<MovieReview> movieReviews;

    if (movieId != null && userId != null)
      movieReviews = movieReviewDao.findAllByMovieIdAndUserId(pageable, movieId, userId);
    else if (movieId != null)
      movieReviews = movieReviewDao.findAllByMovieId(pageable, movieId);
    else if (userId != null)
      movieReviews = movieReviewDao.findAllByUserId(pageable, userId);
    else
      movieReviews = movieReviewDao.findAll(pageable);

    List<MovieReviewDto> result = new ArrayList<>();
    for (MovieReview movieReview : movieReviews) {
      result.add( movieReviewMapper.toDto(movieReview));
    }
    return new PageImpl<>(result, pageable, movieReviews.getTotalElements());
  }

}
