package cmpe273.fandango.dao;

import cmpe273.fandango.entity.Format;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormatDao extends CrudRepository<Format, Integer>{

  @Query("select f.formatId, f.formatName from Format f JOIN f.movieFormats mf where mf.movieId = :movieId")
  List<Format> selectMoiveFormatList(@Param("movieId") Integer movieId);
}
