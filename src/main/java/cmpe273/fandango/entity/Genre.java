package cmpe273.fandango.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="GENRE")
public class Genre {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="genre_id")
  private Integer genreId;

  @Column(name="genre_name")
  private String genreName;

  @OneToMany(mappedBy = "genre")
  private List<MovieGenre> movieGenres;

  public Integer getGenreId() {
    return genreId;
  }

  public void setGenreId(Integer genreId) {
    this.genreId = genreId;
  }

  public String getGenreName() {
    return genreName;
  }

  public void setGenreName(String genreName) {
    this.genreName = genreName;
  }

  public List<MovieGenre> getMovieGenres() {
    return movieGenres;
  }

  public void setMovieGenres(List<MovieGenre> movieGenres) {
    this.movieGenres = movieGenres;
  }
}
