package cmpe273.fandango.service;

import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Boolean createUser(UserDto userDeto);

  Boolean deleteUser(Integer userId);

  UserDto updateUser(Integer userId, UserDto userDto);

  UserDto getUser(Integer userId);

  Page<UserSimpleDto> getAllUsers(Pageable pageable);
}
