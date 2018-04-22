package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name="MOVIE_GENRE")
public class MovieGenre {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="movie_genre_id")
  private Integer movieGenreId;

  @Column(name="movie_id")
  private Integer movieId;

  @ManyToOne
  @JoinColumn(name="genre_id")
  private Genre genre;


  public Integer getMovieGenreId() {
    return movieGenreId;
  }

  public void setMovieGenreId(Integer movieGenreId) {
    this.movieGenreId = movieGenreId;
  }

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

}
