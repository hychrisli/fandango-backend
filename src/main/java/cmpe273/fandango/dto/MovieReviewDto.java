package cmpe273.fandango.dto;

import cmpe273.fandango.entity.Movie;
import cmpe273.fandango.entity.MovieReview;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

public class MovieReviewDto implements Serializable {
  private Integer reviewId;

  private MovieSimpleDto movie;

  private UserSimpleDto user;

  private String reviewTitle;

  private Integer stars;

  private String comment;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "US/Pacific")
  private Date postDate;

  public Integer getReviewId() {
    return reviewId;
  }

  public void setReviewId(Integer reviewId) {
    this.reviewId = reviewId;
  }

  public MovieSimpleDto getMovie() {
    return movie;
  }

  public void setMovie(MovieSimpleDto movie) {
    this.movie = movie;
  }

  public UserSimpleDto getUser() {
    return user;
  }

  public void setUser(UserSimpleDto user) {
    this.user = user;
  }

  public String getReviewTitle() {
    return reviewTitle;
  }

  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  public Integer getStars() {
    return stars;
  }

  public void setStars(Integer stars) {
    this.stars = stars;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }

  public Date getPostDate() {
    return postDate;
  }

  public void setPostDate(Date postDate) {
    this.postDate = postDate;
  }
}
