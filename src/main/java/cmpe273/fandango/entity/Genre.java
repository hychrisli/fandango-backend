package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

  @ManyToMany(mappedBy = "genres")
  @JsonIgnore
  private List<Movie> movies;

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

  public List<Movie> getMovies() {
    return movies;
  }

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
