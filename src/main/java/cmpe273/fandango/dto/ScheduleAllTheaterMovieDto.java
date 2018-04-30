package cmpe273.fandango.dto;

import cmpe273.fandango.entity.City;

import java.io.Serializable;
import java.util.List;

public class ScheduleAllTheaterMovieDto implements Comparable<ScheduleAllTheaterMovieDto>, Serializable {

  private Integer theaterId;

  private String theaterName;

  private String street;

  private String zipcode;

  private City city;

  private List<SchedulePerMovieDto> movies;

  @Override
  public int compareTo(ScheduleAllTheaterMovieDto o) {
    return theaterId - o.getTheaterId();
  }

  public Integer getTheaterId() {
    return theaterId;
  }

  public void setTheaterId(Integer theaterId) {
    this.theaterId = theaterId;
  }

  public String getTheaterName() {
    return theaterName;
  }

  public void setTheaterName(String theaterName) {
    this.theaterName = theaterName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public List<SchedulePerMovieDto> getMovies() {
    return movies;
  }

  public void setMovies(List<SchedulePerMovieDto> movies) {
    this.movies = movies;
  }
}
