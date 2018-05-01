package cmpe273.fandango.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="TICKET_ORDER")
public class TicketOrder implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="order_id")
  private Long orderId;

  @ManyToOne
  @JoinColumn(name="schedule_id")
  private Schedule schedule;

  @ManyToOne
  @JoinColumn(name="user_id")
  private User user;

  @Column(name="ticket_num")
  private Integer ticketNum;

  @Column(name="order_total")
  private Float orderTotal;

  private Float tax;

  @Column(name="grand_total")
  private Float grandTotal;

  @Column(name="order_ts")
  private Date orderTs;

  @Column(name="order_date")
  private Date orderDate;

  @Column(name="order_month")
  private Date orderMonth;

  private Integer status = 1;

  public Long getOrderId() {
    return orderId;
  }

  public void setOrderId(Long orderId) {
    this.orderId = orderId;
  }

  public Schedule getSchedule() {
    return schedule;
  }

  public void setSchedule(Schedule schedule) {
    this.schedule = schedule;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
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

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public Date getOrderMonth() {
    return orderMonth;
  }

  public void setOrderMonth(Date orderMonth) {
    this.orderMonth = orderMonth;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }
}
