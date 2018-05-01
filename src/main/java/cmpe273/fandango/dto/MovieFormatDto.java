package cmpe273.fandango.dto;

import java.io.Serializable;

public class MovieFormatDto implements Serializable {

  private Integer movieId;

  private Integer formatId;

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public Integer getFormatId() {
    return formatId;
  }

  public void setFormatId(Integer formatId) {
    this.formatId = formatId;
  }
}
