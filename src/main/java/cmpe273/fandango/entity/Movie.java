package cmpe273.fandango.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="MOVIE")
public class Movie implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name="movie_id")
  private Integer movieId;

  @Column(name="movie_title")
  private String movieTitle;

  @Column(name="movie_desc")
  private String movieDesc;

  @Column(name="trailer_url")
  private String trailerUrl;

  @Column(name="cover_image_url")
  private String coverImageUrl;

  @Column(name="release_date")
  private Date releaseDate;

  @Column(name="mpaa_id")
  private Integer mpaaId;

  @Column(name="mpaa_rating")
  private String mpaaRating;

  private Float stars = 5.0f;

  private Integer length;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name="MOVIE_GENRE",
      joinColumns = @JoinColumn(name="movie_id", referencedColumnName = "movie_id"),
      inverseJoinColumns = @JoinColumn(name="genre_id", referencedColumnName = "genre_id"))
  @JsonIgnore
  private List<Genre> genres;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name="MOVIE_FORMAT",
      joinColumns = @JoinColumn(name="movie_id", referencedColumnName = "movie_id"),
      inverseJoinColumns = @JoinColumn(name="format_id", referencedColumnName = "format_id"))
  @JsonIgnore
  private List<Format> formats;

  @OneToMany(mappedBy = "movie")
  @JsonIgnore
  private List<MovieCharacter> characters;

  @OneToMany(mappedBy = "movie")
  @JsonIgnore
  private List<MovieImage> images;

  @OneToMany(mappedBy = "movie")
//  @JoinTable(name = "MOVIE_REVIEW",
//          joinColumns = @JoinColumn(name="movie_id", referencedColumnName = "movie_id"),
//          inverseJoinColumns = @JoinColumn(name="review_id", referencedColumnName = "review_id")
//  )
  @JsonIgnore
  private List<MovieReview> review;


  public Integer getMovieId() {
    return movieId;
  }

  public void setMovieId(Integer movieId) {
    this.movieId = movieId;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public void setMovieTitle(String movieTitle) {
    this.movieTitle = movieTitle;
  }

  public String getMovieDesc() {
    return movieDesc;
  }

  public void setMovieDesc(String movieDesc) {
    this.movieDesc = movieDesc;
  }

  public String getTrailerUrl() {
    return trailerUrl;
  }

  public void setTrailerUrl(String trailerUrl) {
    this.trailerUrl = trailerUrl;
  }

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  public Date getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(Date releaseDate) {
    this.releaseDate = releaseDate;
  }

  public Integer getMpaaId() {
    return mpaaId;
  }

  public void setMpaaId(Integer mpaaId) {
    this.mpaaId = mpaaId;
  }

  public Integer getLength() {
    return length;
  }

  public void setLength(Integer length) {
    this.length = length;
  }

  public Float getStars() {
    return stars;
  }

  public void setStars(Float stars) {
    this.stars = stars;
  }

  public String getCoverImageUrl() {
    return coverImageUrl;
  }

  public void setCoverImageUrl(String coverImageUrl) {
    this.coverImageUrl = coverImageUrl;
  }

  public String getMpaaRating() {
    return mpaaRating;
  }

  public void setMpaaRating(String mpaaRating) {
    this.mpaaRating = mpaaRating;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public List<Format> getFormats() {
    return formats;
  }

  public void setFormats(List<Format> formats) {
    this.formats = formats;
  }

  public List<MovieCharacter> getCharacters() {
    return characters;
  }

  public void setCharacters(List<MovieCharacter> characters) {
    this.characters = characters;
  }

  public List<MovieImage> getImages() {
    return images;
  }

  public void setImages(List<MovieImage> images) {
    this.images = images;
  }

  public List<MovieReview> getReview() {
    return review;
  }

  public void setReview(List<MovieReview> review) {
    this.review = review;
  }
}
