package cmpe273.fandango.dto;

import cmpe273.fandango.entity.Format;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Time;

public class ScheduleSimpleDto implements Serializable {

  private Long scheduleId;

  private String format;

  private Time showtime;

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

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
  public Time getShowtime() {
    return showtime;
  }

  public void setShowtime(Time showtime) {
    this.showtime = showtime;
  }

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
}
