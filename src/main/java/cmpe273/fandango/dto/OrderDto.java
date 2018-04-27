package cmpe273.fandango.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class OrderDto {

  private Long orderId;

  private MovieSimpleDto movie;

  private UserSimpleDto user;

  private TheaterDto theater;

  private ScheduleSimpleDto schedule;

  private Integer ticketNum;

  private Float orderTotal;

  private Float tax;

  private Float grandTotal;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:SS", timezone="US/Pacific")
  private Date orderTs;

  private Integer status = 1;


  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public MovieSimpleDto getMovie() {
    return movie;
  }

  public void setMovie(MovieSimpleDto movie) {
    this.movie = movie;
  }

  public UserSimpleDto getUser() {
    return user;
  }

  public void setUser(UserSimpleDto user) {
    this.user = user;
  }

  public TheaterDto getTheater() {
    return theater;
  }

  public void setTheater(TheaterDto theater) {
    this.theater = theater;
  }

  public ScheduleSimpleDto getSchedule() {
    return schedule;
  }

  public void setSchedule(ScheduleSimpleDto schedule) {
    this.schedule = schedule;
  }

  public Integer getTicketNum() {
    return ticketNum;
  }

  public void setTicketNum(Integer ticketNum) {
    this.ticketNum = ticketNum;
  }

  public Float getOrderTotal() {
    return orderTotal;
  }

  public void setOrderTotal(Float orderTotal) {
    this.orderTotal = orderTotal;
  }

  public Float getTax() {
    return tax;
  }

  public void setTax(Float tax) {
    this.tax = tax;
  }

  public Float getGrandTotal() {
    return grandTotal;
  }

  public void setGrandTotal(Float grandTotal) {
    this.grandTotal = grandTotal;
  }

  public Date getOrderTs() {
    return orderTs;
  }

  public void setOrderTs(Date orderTs) {
    this.orderTs = orderTs;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
