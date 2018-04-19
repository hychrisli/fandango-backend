package cmpe273.fandango.dto;

import cmpe273.fandango.entity.User;

public class UserSimpleDto {

  public UserSimpleDto(User user){
    this.userId = user.getUserId();
    this.username = user.getUsername();
    this.firstName = user.getFirstName();
    this.lastName = user.getLastName();
    this.imageUrl = user.getImageUrl();
  }

  private Integer userId;

  private String username;

  private String firstName;

  private String lastName;

  private String imageUrl;

  public Integer getUserId() {
    return userId;
  }

  public String getUsername() {
    return username;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getImageUrl() {
    return imageUrl;
  }
}
