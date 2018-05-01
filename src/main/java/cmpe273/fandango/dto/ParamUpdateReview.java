package cmpe273.fandango.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ParamUpdateReview implements Serializable {

  private String reviewTitle;

  private Integer stars;

  private String comment;

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
