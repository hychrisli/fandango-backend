package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MOVIE_REVIEW")
public class MovieReview {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "review_id")
  private Integer reviewId;

  @Column(name = "review_title")
  private String reviewTitle;

  @Column(name = "stars")
  private Integer stars;

  @Column(name = "comment")
  private String comment;

  @Column(name = "post_date")
  private Date postDate;

  @ManyToOne
  @JoinColumn(name = "movie_id")
  @JsonIgnore
  private Movie movie;

  @ManyToOne
  @JoinColumn(name = "user_id")
  @JsonIgnore
  private User user;

  public Integer getReviewID() {
    return reviewId;
  }

  public void setReviewID(Integer reviewID) {
    this.reviewId = reviewID;
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

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Integer getReviewId() {
    return reviewId;
  }

  public void setReviewId(Integer reviewId) {
    this.reviewId = reviewId;
  }

  public String getReviewTitle() {
    return reviewTitle;
  }

  public void setReviewTitle(String reviewTitle) {
    this.reviewTitle = reviewTitle;
  }

  public Date getPostDate() {
    return postDate;
  }

  public void setPostDate(Date postDate) {
    this.postDate = postDate;
  }
}
