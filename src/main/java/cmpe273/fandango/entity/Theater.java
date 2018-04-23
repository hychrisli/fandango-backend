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

  private String zipcode;

  @ManyToOne
  @JoinColumn(name="city_id")
  private City city;

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

  public String getZipcode() {
    return zipcode;
  }

  public void setZipcode(String zipcode) {
    this.zipcode = zipcode;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }
}
