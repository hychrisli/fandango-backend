package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="MOVIE_GENRE")
public class MovieGenre implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="movie_genre_id")
  private Integer movieGenreId;

  @ManyToOne
  @JoinColumn(name="movie_id")
  @JsonIgnore
  private Movie movie;

  @ManyToOne
  @JoinColumn(name="genre_id")
  @JsonIgnore
  private Genre genre;


  public Integer getMovieGenreId() {
    return movieGenreId;
  }

  public void setMovieGenreId(Integer movieGenreId) {
    this.movieGenreId = movieGenreId;
  }

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public Genre getGenre() {
    return genre;
  }

  public void setGenre(Genre genre) {
    this.genre = genre;
  }
}
