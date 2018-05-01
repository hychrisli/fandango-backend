package cmpe273.fandango.dto;

import java.io.Serializable;

public class MovieGenreDto implements Serializable {

  private Integer movieId;

  private Integer genreId;

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public Integer getGenreId() {
    return genreId;
  }

  public void setGenreId(Integer genreId) {
    this.genreId = genreId;
  }
}
