package cmpe273.fandango.service;

import cmpe273.fandango.dto.ParamLogin;
import cmpe273.fandango.dto.ParamCreateUser;
import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  UserDto createUser(ParamCreateUser paramCreateUser);

  Boolean deleteUser(Integer userId);

  UserDto updateUser(Integer userId, UserDto userDto) throws AppException;

  UserDto getUser(Integer userId);

  User getUserByUsername(String username);

  UserDto loginUser(ParamLogin paramLogin);

  Page<UserSimpleDto> getAllUsers(Pageable pageable);
}
