package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.entity.Format;
import cmpe273.fandango.entity.Genre;

import java.util.List;

public interface CategoryService {

  List<Format> getAllFormats ();

  List<Genre> getAllGenres();
}
