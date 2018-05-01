package cmpe273.fandango.dto;

import java.io.Serializable;

public class RevenueTheaterDto implements Serializable {

  private TheaterDto theater;

  private Double revenue = 0.0;

  private Long tickets = 0L;

  public TheaterDto getTheater() {
    return theater;
  }

  public void setTheater(TheaterDto theater) {
    this.theater = theater;
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
