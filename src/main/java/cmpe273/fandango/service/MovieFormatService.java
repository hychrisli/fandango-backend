package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieDto;
import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.entity.Format;

import java.util.List;

public interface MovieFormatService {

  List<Format> getAllFormats ();

  MovieDto addFormat (MovieFormatDto movieFormatDto);

  MovieDto removeFormat (MovieFormatDto movieFormatDto);
}
