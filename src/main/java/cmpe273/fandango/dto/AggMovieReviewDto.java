package cmpe273.fandango.dto;

import java.io.Serializable;

public class AggMovieReviewDto implements Serializable{

  private MovieSimpleDto movie;

  private Long reivewNum;

  private Integer minStars;

  private Integer maxStars;

  public MovieSimpleDto getMovie() {
    return movie;
  }

  public void setMovie(MovieSimpleDto movie) {
    this.movie = movie;
  }

  public Long getReivewNum() {
    return reivewNum;
  }

  public void setReivewNum(Long reivewNum) {
    this.reivewNum = reivewNum;
  }

  public Integer getMinStars() {
    return minStars;
  }

  public void setMinStars(Integer minStars) {
    this.minStars = minStars;
  }

  public Integer getMaxStars() {
    return maxStars;
  }

  public void setMaxStars(Integer highStars) {
    this.maxStars = highStars;
  }
}
