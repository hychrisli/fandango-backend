package cmpe273.fandango.dao;

import cmpe273.fandango.entity.MovieFormat;
import org.springframework.data.repository.CrudRepository;

public interface MovieFormatDao extends CrudRepository<MovieFormat, Integer>{
}
