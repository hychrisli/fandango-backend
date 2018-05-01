package cmpe273.fandango.dto;

import java.io.Serializable;

public class RevenueMovieDto implements Serializable {

  private MovieSimpleDto movie;

  private Double revenue = 0.0;

  private Long tickets = 0L;

  public MovieSimpleDto getMovie() {
    return movie;
  }

  public void setMovie(MovieSimpleDto movie) {
    this.movie = movie;
  }

  public Double getRevenue() {
    return revenue;
  }

  public void setRevenue(Double revenue) {
    this.revenue = revenue;
  }

  public Long getTickets() {
    return tickets;
  }

  public void setTickets(Long tickets) {
    this.tickets = tickets;
  }
}
