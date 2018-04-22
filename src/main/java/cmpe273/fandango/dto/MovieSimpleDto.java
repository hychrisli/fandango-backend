package cmpe273.fandango.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class MovieSimpleDto {

  private Integer movieId;

  private String movieTitle;

  private String movieDesc;

  private String trailerUrl;

  private String coverImageUrl;

  private Date releaseDate;

  private String mpaaRating;

  private Float stars;

  private Integer length;

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public String getMovieDesc() {
    return movieDesc;
  }

  public void setMovieDesc(String movieDesc) {
    this.movieDesc = movieDesc;
  }

  public String getTrailerUrl() {
    return trailerUrl;
  }

  public void setTrailerUrl(String trailerUrl) {
    this.trailerUrl = trailerUrl;
  }

  public String getCoverImageUrl() {
    return coverImageUrl;
  }

  public void setCoverImageUrl(String coverImageUrl) {
    this.coverImageUrl = coverImageUrl;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public String getMpaaRating() {
    return mpaaRating;
  }

  public void setMpaaRating(String mpaaRating) {
    this.mpaaRating = mpaaRating;
  }

  public Float getStars() {
    return stars;
  }

  public void setStars(Float stars) {
    this.stars = stars;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }
}
