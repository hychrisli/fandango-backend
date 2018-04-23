package cmpe273.fandango.constant;

public interface UrlConstant {

  String USER = "/user";
  String USERS = "/users";
  String USER_USERID = "/user/{userId}";
  String LOGIN = "/login";

  String FORMATS = "/formats";
  String GENRES = "/genres";

  String MOVIE = "/movie";
  String MOVIES = "/movies";
  String MOVIE_MOVIEID = "/movie/{movieId}";

  String MOVIE_FORMAT = "/movie-format";
  String MOVIE_FORMAT_MOVIEID = "/movie-format/{movieId}";

  String MOVIE_GENRE = "/movie-genre";
  String MOVIE_GENRE_MOVIEID = "/movie-genre/{movieId}";

  String MOVIE_CHARACTER = "/movie-character";
  String MOVIE_CHARACTER_MOVIEID = "/movie-character/{movieId}";

  String MOVIE_IMAGE = "/movie-image";
  String MOVIE_IMAGE_MOVIEID = "/movie-image/{movieId}";

  String CITY = "/city";
  String CITIES = "/cities";
  String CITY_CITYID = "/city/{cityId}";

  String THEATER = "/theater";
  String THEATERS_CITYID = "/theaters/{cityId}";
  String THEATER_THEATERID = "/theater/{theaterId}";

  String SCHEDULES = "/schedules";

  String HOST = "http://localhost";

}
