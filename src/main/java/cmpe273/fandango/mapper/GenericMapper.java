package cmpe273.fandango.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.util.function.Consumer;

public class GenericMapper {

  protected ModelMapper modelMapper;

  public GenericMapper() {
    modelMapper = new ModelMapper();
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  protected <T> void updateValue(Consumer<T> setterMethod, T value) {
    if (value != null){
      setterMethod.accept(value);
    }
  }

  protected <T1, T2> T2 mapT1toT2 (T1 a, T2 b){
    if ( a == null) return null;
    modelMapper.map(a, b);
    return b;
  }

}
