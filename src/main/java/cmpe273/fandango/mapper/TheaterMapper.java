package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.TheaterDto;
import cmpe273.fandango.entity.Theater;

public class TheaterMapper extends GenericMapper{

  public Theater toPojo ( TheaterDto dto) {
    return mapT1toT2(dto, new Theater());
  }

}
