package cmpe273.fandango.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class ParamCreateOrder implements Serializable {

  @NotNull
  private Long scheduleId;

  @NotNull
  private Integer userId;

  @NotNull
  private Integer ticketNum;

  public Long getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(Long scheduleId) {
    this.scheduleId = scheduleId;
  }

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public Integer getTicketNum() {
    return ticketNum;
  }

  public void setTicketNum(Integer ticketNum) {
    this.ticketNum = ticketNum;
  }
}
