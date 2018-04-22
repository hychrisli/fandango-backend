package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name="MPAA_RATING")
public class MpaaRating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="mpaa_id")
  private Integer mpaaId;

  @Column(name="mpaa_name")
  private String mpaaName;

  public Integer getMpaaId() {
    return mpaaId;
  }

  public void setMpaaId(Integer mpaaId) {
    this.mpaaId = mpaaId;
  }

  public String getMpaaName() {
    return mpaaName;
  }

  public void setMpaaName(String mpaaName) {
    this.mpaaName = mpaaName;
  }
}
