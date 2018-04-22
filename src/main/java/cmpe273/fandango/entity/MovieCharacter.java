package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name = "MOVIE_CHARACTER")
public class MovieCharacter {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "character_id")
  private Integer characterId;

  @Column(name = "movie_id")
  private Integer movieId;

  @Column(name = "character_name")
  private String characterName;

  public Integer getCharacterId() {
    return characterId;
  }

  public void setCharacterId(Integer characterId) {
    this.characterId = characterId;
  }

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public String getCharacterName() {
    return characterName;
  }

  public void setCharacterName(String characterName) {
    this.characterName = characterName;
  }
}
