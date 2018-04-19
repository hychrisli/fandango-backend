package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;

public class UserMapper {

  public static UserSimpleDto toSimpleDto(User user){
    return new UserSimpleDto(user);
  }
}
