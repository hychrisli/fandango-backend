package cmpe273.fandango.dto;

public class ParamUpdateTheater {

  private String theaterName;

  private String street;

  private String zipcode;

  private Integer cityId;

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

  public Integer getCityId() {
    return cityId;
  }

  public void setCityId(Integer cityId) {
    this.cityId = cityId;
  }
}
