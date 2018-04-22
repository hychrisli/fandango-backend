package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="MOVIE")
public class Movie {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="movie_id")
  private Integer movieId;

  @Column(name="movie_title")
  private String movieTitle;

  @Column(name="movie_desc")
  private String movieDesc;

  @Column(name="trailer_url")
  private String trailerUrl;

  @Column(name="cover_image_url")
  private String coverImageUrl;

  @Column(name="release_date")
  private Date releaseDate;

  @Column(name="mpaa_id")
  private Integer mpaaId;

  @Column(name="mpaa_rating")
  private String mpaaRating;

  private Integer stars;

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

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Integer getMpaaId() {
    return mpaaId;
  }

  public void setMpaaId(Integer mpaaId) {
    this.mpaaId = mpaaId;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Integer getStars() {
    return stars;
  }

  public void setStars(Integer stars) {
    this.stars = stars;
  }

  public String getCoverImageUrl() {
    return coverImageUrl;
  }

  public void setCoverImageUrl(String coverImageUrl) {
    this.coverImageUrl = coverImageUrl;
  }

  public String getMpaaRating() {
    return mpaaRating;
  }

  public void setMpaaRating(String mpaaRating) {
    this.mpaaRating = mpaaRating;
  }
}
