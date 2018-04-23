package cmpe273.fandango.entity;

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
  Movie movie;

  @ManyToOne
  @JoinColumn(name="theater_id")
  Theater theater;

  @ManyToOne
  @JoinColumn(name="format_id")
  Format format;

  @Column(name="schedule_date")
  private Date scheduleDate;

  private Time showtime;

  @Column(name="tot_seats")
  private Integer totSeats;

  @Column(name="avail_seats")
  private Integer availSeats;

  private Float price;



}
