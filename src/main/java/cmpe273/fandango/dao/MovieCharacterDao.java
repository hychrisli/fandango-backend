package cmpe273.fandango.dao;

import cmpe273.fandango.entity.MovieCharacter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MovieCharacterDao extends CrudRepository<MovieCharacter, Integer> {

}
