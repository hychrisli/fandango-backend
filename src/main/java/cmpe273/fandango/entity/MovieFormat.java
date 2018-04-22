package cmpe273.fandango.entity;


import javax.persistence.*;

@Entity
@Table(name="MOVIE_FORMAT")
public class MovieFormat {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="movie_format_id")
  private Integer movieFormatId;

  @ManyToOne
  @JoinColumn(name="format_id")
  private Format format;

  @Column(name="movie_id")
  private Integer movieId;

}
