package cmpe273.fandango.service;

import cmpe273.fandango.dto.MovieFormatDto;
import cmpe273.fandango.entity.Format;

import java.util.List;

public interface MovieFormatService {

  List<Format>  getAllFormats ();

  MovieFormatDto AddFormat (Integer movieId, Integer formatId);

  MovieFormatDto RemoveFormat (Integer movieId, Integer formatId);
}
