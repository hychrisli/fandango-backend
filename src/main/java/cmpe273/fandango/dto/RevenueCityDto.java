package cmpe273.fandango.dto;

import cmpe273.fandango.entity.City;

import java.io.Serializable;

public class RevenueCityDto implements Serializable{

  private City city;

  private Double revenue = 0.0;

  private Long tickets = 0L;

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
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
