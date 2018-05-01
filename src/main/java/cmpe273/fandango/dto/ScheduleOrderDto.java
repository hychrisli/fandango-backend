package cmpe273.fandango.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;

public class ScheduleOrderDto implements Serializable {

  private Long scheduleId;

  private String format;

  private Time showtime;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="US/Pacific")
  private Date scheduleDate;

  private Float price;

  public Long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }

  public String getFormat() {
    return format;
  }

  public void setFormat(String format) {
    this.format = format;
  }

  public Time getShowtime() {
    return showtime;
  }

  public void setShowtime(Time showtime) {
    this.showtime = showtime;
  }

  public Date getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(Date scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
}
