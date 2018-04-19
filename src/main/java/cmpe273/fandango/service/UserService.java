package cmpe273.fandango.service;

import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  Boolean createUser(User user);

  Boolean deleteUser(Integer userId);

  User updateUser(User user);

  User getUser(Integer userId);

  Page<UserSimpleDto> getAllUsers(Pageable pageable);
}
