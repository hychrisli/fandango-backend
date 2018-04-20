package cmpe273.fandango.service;

import cmpe273.fandango.dto.LoginDto;
import cmpe273.fandango.dto.UserCreateDto;
import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  UserDto createUser(UserCreateDto userCreateDto);

  Boolean deleteUser(Integer userId);

  UserDto updateUser(Integer userId, UserDto userDto);

  UserDto getUser(Integer userId);

  User getUserByUsername(String username);

  UserDto loginUser(LoginDto loginDto);

  Page<UserSimpleDto> getAllUsers(Pageable pageable);
}
