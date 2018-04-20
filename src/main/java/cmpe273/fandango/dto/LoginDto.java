package cmpe273.fandango.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class LoginDto {

  @NotNull
  @JsonProperty(required=true)
  private String username;

  @NotNull
  @JsonProperty(required=true)
  private String password;

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
}
