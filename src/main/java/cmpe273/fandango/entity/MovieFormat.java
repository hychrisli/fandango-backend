package cmpe273.fandango.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MOVIE_FORMAT")
public class MovieFormat implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="movie_format_id")
  private Integer movieFormatId;

  @ManyToOne
  @JoinColumn(name="movie_id")
  @JsonIgnore
  private Movie movie;

  @ManyToOne
  @JoinColumn(name="format_id")
  @JsonIgnore
  private Format format;

}
