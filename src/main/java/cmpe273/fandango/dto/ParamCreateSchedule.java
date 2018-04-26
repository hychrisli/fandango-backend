package cmpe273.fandango.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import java.sql.Time;
import java.util.Date;

public class ParamCreateSchedule {

  @NotNull
  private Integer movieId;

  @NotNull
  private Integer theaterId;

  @NotNull
  private Integer formatId;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="US/Pacific")
  @NotNull
  private Date scheduleDate;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss")
  @NotNull
  private Time showtime;

  private Integer totSeats = 50;

  private Integer availSeats = 50;

  private Float Price = 15.0f;

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public Integer getTheaterId() {
    return theaterId;
  }

  public void setTheaterId(Integer theaterId) {
    this.theaterId = theaterId;
  }

  public Integer getFormatId() {
    return formatId;
  }

  public void setFormatId(Integer formatId) {
    this.formatId = formatId;
  }

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
