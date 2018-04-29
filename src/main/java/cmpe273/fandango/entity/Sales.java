package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name="SALES")
public class Sales {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="sales_id")
  private Long salesId;

  @ManyToOne
  @JoinColumn(name="movie_id")
  private Movie movie;

  @ManyToOne
  @JoinColumn(name="theater_id")
  private Theater theater;

  @Column(name="ticket_num")
  private Integer ticketNum = 0;

  @Column(name="dollar_amount")
  private Double dollarAmount = 0.0;


  public Long getSalesId() {
    return salesId;
  }

  public void setSalesId(Long salesId) {
    this.salesId = salesId;
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

  public Integer getTicketNum() {
    return ticketNum;
  }

  public void setTicketNum(Integer ticketNum) {
    this.ticketNum = ticketNum;
  }

  public Double getDollarAmount() {
    return dollarAmount;
  }

  public void setDollarAmount(Double dollarAmount) {
    this.dollarAmount = dollarAmount;
  }
}
