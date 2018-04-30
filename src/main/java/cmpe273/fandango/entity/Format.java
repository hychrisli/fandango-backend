package cmpe273.fandango.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="FORMAT")
public class Format implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="format_id")
  private Integer formatId;

  @Column(name="format_name")
  private String formatName;

  @ManyToMany(mappedBy = "formats")
  @JsonIgnore
  private List<Movie> movies;

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

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
