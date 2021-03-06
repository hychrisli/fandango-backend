package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="USER")
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="user_id")
  private Integer userId;

  @NotNull
  private String username;

  private String password;

  @Column(name="first_name")
  private String firstName;

  @Column(name="last_name")
  private String lastName;

  private String street;

  private String city;

  private String state;

  private String zipcode;

  private String email;

  private String phone;

  @Column(name="image_url")
  private String imageUrl;

  private Integer role = 1;

  @Column(name="card_num")
  private String cardNum;

  @Column(name="card_cvv")
  private String cardCvv;

  @Column(name="card_expire")
  private Date cardExpire;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<MovieReview> review;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer useId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public List<MovieReview> getReview() {
    return review;
  }

  public void setReview(List<MovieReview> review) {
    this.review = review;
  }

  public Integer getRole() {
    return role;
  }

  public void setRole(Integer role) {
    this.role = role;
  }

  public String getCardNum() {
    return cardNum;
  }

  public void setCardNum(String cardNum) {
    this.cardNum = cardNum;
  }

  public String getCardCvv() {
    return cardCvv;
  }

  public void setCardCvv(String cardCvv) {
    this.cardCvv = cardCvv;
  }

  public Date getCardExpire() {
    return cardExpire;
  }

  public void setCardExpire(Date cardExpire) {
    this.cardExpire = cardExpire;
  }
}