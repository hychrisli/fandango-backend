package cmpe273.fandango.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;


  @NotNull
  private String email;


  @NotNull
  private String name;


  // Public methods

  public User() { }

  public User(long id) {
    this.id = id;
  }

  public User(String email, String name) {
    this.email = email;
    this.name = name;
  }

  // Getters and setters methods
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}