package cmpe273.fandango.dto;

import java.io.Serializable;
import java.util.List;

public class MovieSearchDto implements Comparable<MovieSearchDto>, Serializable{

  private Integer movieId;

  private String movieTitle;

  private String movieDesc;

  private String trailerUrl;

  private String coverImageUrl;

  private String mpaaRating;

  private Float stars;

  private Integer length;

  private List<String> formatNames;

  private Float minPrice = 100.0f;

  @Override
  public int compareTo(MovieSearchDto o)
  {
    return(movieId - o.getMovieId());
  }

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

  public List<String> getFormatNames() {
    return formatNames;
  }

  public void setFormatNames(List<String> formatNames) {
    this.formatNames = formatNames;
  }

  public Float getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Float minPrice) {
    this.minPrice = minPrice;
  }
}
