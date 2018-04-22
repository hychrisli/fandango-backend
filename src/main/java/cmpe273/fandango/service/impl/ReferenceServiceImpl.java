package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.FormatDao;
import cmpe273.fandango.dao.GenreDao;
import cmpe273.fandango.entity.Format;
import cmpe273.fandango.entity.Genre;
import cmpe273.fandango.service.ReferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReferenceServiceImpl implements ReferenceService {

  @Autowired
  FormatDao formatDao;

  @Autowired
  GenreDao genreDao;

  @Override
  public List<Format> getAllFormats() {
    return formatDao.findAll();
  }

  @Override
  public List<Genre> getAllGenres() {
    return genreDao.findAll();
  }
}
