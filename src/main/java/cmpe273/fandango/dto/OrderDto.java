package cmpe273.fandango.dto;

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

  private Date orderTs;

  private Integer status = 1;

}
