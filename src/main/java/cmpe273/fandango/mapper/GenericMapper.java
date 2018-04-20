package cmpe273.fandango.mapper;

import org.modelmapper.ModelMapper;

import java.util.function.Consumer;

public abstract class GenericMapper<POJO, DTO> {

  protected ModelMapper modelMapper = new ModelMapper();

  public abstract DTO toDto(POJO pojo);

  public abstract POJO toPojo(DTO dto);

  protected <T> void updateValue(Consumer<T> setterMethod, T value) {
    if (value != null){
      setterMethod.accept(value);
    }
  }
}
