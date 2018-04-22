package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name="THEATER")
public class Theater {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="theater_id")
  private Integer theaterId;

  @Column(name="theater_name")
  private String theaterName;

  private String street;

  private String city;

  private String state;

  private String zipcode;

  public Integer getTheaterId() {
    return theaterId;
  }

  public void setTheaterId(Integer theaterId) {
    this.theaterId = theaterId;
  }

  public String getTheaterName() {
    return theaterName;
  }

  public void setTheaterName(String theaterName) {
    this.theaterName = theaterName;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }
}
