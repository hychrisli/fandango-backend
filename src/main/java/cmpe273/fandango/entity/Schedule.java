package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="SCHEDULE")
public class Schedule {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="schedule_id")
  private Long scheduleId;

  @ManyToOne
  @JoinColumn(name="movie_id")
  private Movie movie;

  @ManyToOne
  @JoinColumn(name="theater_id")
  private Theater theater;

  @ManyToOne
  @JoinColumn(name="format_id")
  private Format format;

  @Column(name="schedule_date")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="US/Pacific")
  private Date scheduleDate;

  private Time showtime;

  @Column(name="tot_seats")
  private Integer totSeats;

  @Column(name="avail_seats")
  private Integer availSeats;

  private Float price;

  public Long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Theater getTheater() {
    return theater;
  }

  public void setTheater(Theater theater) {
    this.theater = theater;
  }

  public Format getFormat() {
    return format;
  }

  public void setFormat(Format format) {
    this.format = format;
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
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }
}
