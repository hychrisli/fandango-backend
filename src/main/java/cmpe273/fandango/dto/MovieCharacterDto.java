package cmpe273.fandango.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(description = "A Movie Character")
public class MovieCharacterDto implements Serializable{

  @ApiModelProperty(value = "movie Id", required = true)
  private Integer movieId;

  private Integer characterId;

  private String characterName;

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public Integer getCharacterId() {
    return characterId;
  }

  public void setCharacterId(Integer characterId) {
    this.characterId = characterId;
  }

  public String getCharacterName() {
    return characterName;
  }

  public void setCharacterName(String characterName) {
    this.characterName = characterName;
  }
}
