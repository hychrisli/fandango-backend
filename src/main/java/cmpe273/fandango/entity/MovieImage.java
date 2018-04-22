package cmpe273.fandango.entity;

import javax.persistence.*;

@Entity
@Table(name="MOVIE_IMAGE")
public class MovieImage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="image_id")
  private Integer imageId;

  @Column(name="movie_id")
  private Integer movieId;

  @Column(name="image_title")
  private String imageTitle;

  @Column(name="image_desc")
  private String imageDesc;

  @Column(name="image_url")
  private String imageUrl;

  public Integer getImageId() {
    return imageId;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
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
}
