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
  String THEATER_MOVIES_CITYID = "/theater-movies/{cityId}";

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
  String THEATERS_MOVIES_ZIPCDOE = "/theaters-movies-zipcode/{zipcode}";
  String THEATERS_MOVIES_CITYID = "/theaters-movies-cityid/{cityId}";

  String SEARCH_CITIES = "/search-cities/{pattern}";
  String SEARCH_THEATERS = "/search-theaters/{pattern}";
  String SEARCH_MOVIES = "/search-movies/{pattern}";

  String SCHEDULES = "/schedules";
  String SCHEDULE = "/schedule";
  String SCHEDULE_ID = "/schedule/{scheduleId}";
  String SCHEDULES_CITYID = "/schedules-city/{cityId}";
  String SCHEDULES_CITYID_MOVIEID = "/schedules-city/{cityId}/{movieId}";
  String SCHEDULES_ZIPCODE = "/schedules-zipcdoe/{zipcode}";
  String SCHEDULES_ZIPCODE_MOVIEID = "/schedules-zipcode/{zipcode}/{movieId}";
  String SCHEDULES_THEATER = "/schedules-theater/{theaterId}";

  String HOST = "http://localhost";

  String MOVIE_REVIEW = "/movie-review";
  String MOVIE_REVIEW_ID = "/movie-review/{reviewId}";
  String MOVIE_REVIEWS = "/movie-reviews";

}
