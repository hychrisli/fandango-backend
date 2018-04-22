package cmpe273.fandango.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="FORMAT")
public class Format {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="format_id")
  private Integer formatId;

  @Column(name="format_name")
  private String formatName;

  @OneToMany(mappedBy = "format")
  private List<MovieFormat> movieFormats;

  public Integer getFormatId() {
    return formatId;
  }

  public void setFormatId(Integer formatId) {
    this.formatId = formatId;
  }

  public String getFormatName() {
    return formatName;
  }

  public void setFormatName(String formatName) {
    this.formatName = formatName;
  }

  public List<MovieFormat> getMovieFormats() {
    return movieFormats;
  }

  public void setMovieFormats(List<MovieFormat> movieFormats) {
    this.movieFormats = movieFormats;
  }
}
