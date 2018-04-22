package cmpe273.fandango.mapper;

import cmpe273.fandango.dto.UserCreateDto;
import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;

public class UserMapper extends AbstractMapper<User, UserDto> {

  @Override
  public UserDto toDto(User pojo){
    if ( pojo == null ) return null;
    return modelMapper.map(pojo, UserDto.class);
  }

  @Override
  public User toPojo (UserDto dto) {
    if ( dto == null ) return null;
    return modelMapper.map(dto, User.class);
  }

  public User toPojo (UserCreateDto dto) {
    if (dto == null) return null;
    return modelMapper.map(dto, User.class);
  }

  public UserSimpleDto toSimpleDto(User pojo){
    if (pojo == null)  return null;
    return modelMapper.map(pojo, UserSimpleDto.class);
  }

  public User updPojo (UserDto dto, User pojo){
    if ( dto == null ) return pojo;
    updateValue(pojo::setCity, dto.getCity());
    updateValue(pojo::setCreditCard, dto.getCreditCard());
    updateValue(pojo::setEmail, dto.getEmail());
    updateValue(pojo::setFirstName, dto.getFirstName());
    updateValue(pojo::setImageUrl, dto.getImageUrl());
    updateValue(pojo::setLastName, dto.getLastName());
    updateValue(pojo::setPassword, dto.getPassword());
    updateValue(pojo::setPhone, dto.getPhone());
    updateValue(pojo::setState, dto.getState());
    updateValue(pojo::setStreet, dto.getStreet());
    updateValue(pojo::setUsername, dto.getUsername());
    updateValue(pojo::setZipcode, dto.getZipcode());
    return pojo;
  }
}
