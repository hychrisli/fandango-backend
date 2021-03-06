package cmpe273.fandango.dto;

import cmpe273.fandango.entity.Format;
import cmpe273.fandango.entity.Genre;
import cmpe273.fandango.entity.MovieCharacter;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SchedulePerMovieDto implements Comparable<SchedulePerMovieDto>, Serializable{

  private Integer movieId;

  private String movieTitle;

  private String movieDesc;

  private String trailerUrl;

  private String coverImageUrl;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="US/Pacific")
  private Date releaseDate;

  private String mpaaRating;

  private Float stars;

  private Integer length;

  private List<Genre> genres;

  private List<Format> formats;

  private List<MovieCharacter> characters;

  private List<ScheduleSimpleDto> schedules;


  @Override
  public int compareTo(SchedulePerMovieDto o) {
    return - releaseDate.compareTo(o.getReleaseDate());
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

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public List<Format> getFormats() {
    return formats;
  }

  public void setFormats(List<Format> formats) {
    this.formats = formats;
  }

  public List<MovieCharacter> getCharacters() {
    return characters;
  }

  public void setCharacters(List<MovieCharacter> characters) {
    this.characters = characters;
  }

  public List<ScheduleSimpleDto> getSchedules() {
    return schedules;
  }

  public void setSchedules(List<ScheduleSimpleDto> schedules) {
    this.schedules = schedules;
  }
}
