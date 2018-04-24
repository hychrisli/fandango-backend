package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.dto.MovieReviewDto;
import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.service.MovieReviewService;
import cmpe273.fandango.dao.MovieReviewDao;
import cmpe273.fandango.entity.MovieReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cmpe273.fandango.dao.MovieReviewDao;
import cmpe273.fandango.mapper.MovieReviewMapper;
import cmpe273.fandango.dao.MovieDao;

import java.util.List;

@Service
public class MovieReviewServiceImpl implements MovieReviewService {
    @Autowired
    MovieReviewDao movieReviewDao;

    @Autowired
    MovieDao movieDao;

    @Autowired
    UserDao userDao;

    private MovieReviewMapper movieReviewMapper = new MovieReviewMapper();

    @Override
    public MovieReviewDto getMovieReviews (Integer reviewId) {
        MovieReview movieReview = movieReviewDao.SelectAllByReviewId(reviewId);
        if (movieReview != null) {
            MovieReviewDto movieReviewDto = movieReviewMapper.toDto(movieReview);
            return movieReviewDto;
        }
        return null;
    }

    public List<MovieReview> addReview(MovieReviewDto movieReviewDto) {
        Movie movie = movieDao.findOne(movieReviewDto.getMovieId());
        if (movie == null) {
            return null;
        }
        MovieReview movieReview = new MovieReview();
        if (movieReviewDto.getUserId() != null) {

            User user = userDao.findUserByUserId(movieReviewDto.getUserId());
            movieReview.setUser(user);
        }


        movieReview.setMovie(movie);
        movieReview.setComment(movieReviewDto.getComment());
        movieReview.setStars(movieReviewDto.getStars());
        movieReviewDao.save(movieReview);
        return movieReviewDao.SelectByReviewId(movieReviewDto.getMovieId());


    }

    public List<MovieReview> removeReview(MovieReviewDto movieReviewDto) {
        Movie movie = movieDao.findOne(movieReviewDto.getMovieId());
        MovieReview movieReview = movieReviewDao.findOne(movieReviewDto.getReviewId());
        if (movie == null || movieReview == null) {
            return null;
        }
        if (movie.getReview().contains(movieReview)) {
            movieReviewDao.delete(movieReviewDto.getReviewId());
        }
        return movieReviewDao.SelectByReviewId(movieReviewDto.getMovieId());
    }


}
