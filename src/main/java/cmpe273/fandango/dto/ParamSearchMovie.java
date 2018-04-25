package cmpe273.fandango.dto;

import javax.validation.constraints.NotNull;

public class ParamSearchMovie {

  private Float maxPrice = 100.0f;

  private Float minPrice = 0.0f;

  private Float maxStars = 5.0f;

  private Float minStars = 0.0f;

  private Integer MpaaId;

  private Integer formatId;

  public Float getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(Float maxPrice) {
    this.maxPrice = maxPrice;
  }

  public Float getMinPrice() {
    return minPrice;
  }

  public void setMinPrice(Float minPrice) {
    this.minPrice = minPrice;
  }

  public Float getMaxStars() {
    return maxStars;
  }

  public void setMaxStars(Float maxStars) {
    this.maxStars = maxStars;
  }

  public Float getMinStars() {
    return minStars;
  }

  public void setMinStars(Float minStars) {
    this.minStars = minStars;
  }

  public Integer getMpaaId() {
    return MpaaId;
  }

  public void setMpaaId(Integer mpaaId) {
    MpaaId = mpaaId;
  }

  public Integer getFormatId() {
    return formatId;
  }

  public void setFormatId(Integer formatId) {
    this.formatId = formatId;
  }

}
