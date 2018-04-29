package cmpe273.fandango.dto;

import cmpe273.fandango.lib.DateTime;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

public class ParamFilterSchedule implements Serializable{

  private Float minPrice;

  private Float maxPrice;

  private Integer minStars;

  private Integer maxStars;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  @Temporal(TemporalType.DATE)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone="US/Pacific")
  private Date scheduleDate = DateTime.getToday();

  public Float getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Float minPrice) {
    this.minPrice = minPrice;
  }

  public Float getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Float maxPrice) {
    this.maxPrice = maxPrice;
  }

  public Integer getMinStars() {
    return minStars;
  }

  public void setMinStars(Integer minStars) {
    this.minStars = minStars;
  }

  public Integer getMaxStars() {
    return maxStars;
  }

  public void setMaxStars(Integer maxStars) {
    this.maxStars = maxStars;
  }

  public Date getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(Date scheudleDate) {
    this.scheduleDate = scheudleDate;
  }
}
