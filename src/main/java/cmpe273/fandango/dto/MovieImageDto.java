package cmpe273.fandango.dto;

public class MovieImageDto {

  private Integer imageId;

  private String imageTitle;

  private String imageDesc;

  private String imageUrl;

  private Integer movieId;

  public Integer getImageId() {
    return imageId;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public String getImageTitle() {
    return imageTitle;
  }

  public void setImageTitle(String imageTitle) {
    this.imageTitle = imageTitle;
  }

  public String getImageDesc() {
    return imageDesc;
  }

  public void setImageDesc(String imageDesc) {
    this.imageDesc = imageDesc;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }
}
