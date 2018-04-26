package cmpe273.fandango.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Time;
import java.util.Date;

public class ParamUpdateSchedule {

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="US/Pacific")
  private Date scheduleDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
  private Time showtime;

  private Integer totSeats;

  private Integer availSeats;

  private Float Price;

  public Date getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(Date scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  public Time getShowtime() {
    return showtime;
  }

  public void setShowtime(Time showtime) {
    this.showtime = showtime;
  }

  public Integer getTotSeats() {
    return totSeats;
  }

  public void setTotSeats(Integer totSeats) {
    this.totSeats = totSeats;
  }

  public Integer getAvailSeats() {
    return availSeats;
  }

  public void setAvailSeats(Integer availSeats) {
    this.availSeats = availSeats;
  }

  public Float getPrice() {
    return Price;
  }

  public void setPrice(Float price) {
    Price = price;
  }
}
