package cmpe273.fandango.dao;

import cmpe273.fandango.entity.MovieImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieImageDao extends CrudRepository<MovieImage, Integer>{
}
