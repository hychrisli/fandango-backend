package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name="MOVIE_IMAGE")
public class MovieImage {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="image_id")
  private Integer imageId;

  @Column(name="image_title")
  private String imageTitle;

  @Column(name="image_desc")
  private String imageDesc;

  @Column(name="image_url")
  private String imageUrl;

  @ManyToOne
  @JoinColumn(name="movie_id")
  @JsonIgnore
  private Movie movie;

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

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }
}
