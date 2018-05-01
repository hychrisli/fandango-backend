package cmpe273.fandango.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ParamCreateReview implements Serializable {

  @NotNull
  private Integer movieId;

  @NotNull
  private Integer userId;

  @NotNull
  private String reviewTitle;

  @NotNull
  private Integer stars = 5;

  private String comment;

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
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
}
