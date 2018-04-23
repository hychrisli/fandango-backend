package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name="CITY")
public class City {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="city_id")
  private Integer cityId;

  @Column(name="city_name")
  private String cityName;

  @Column(length=2)
  private String state;

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }

  public String getCityName() {
    return cityName;
  }

  public void setCityName(String cityName) {
    this.cityName = cityName;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }
}
