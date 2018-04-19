package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  UserDao userDao;

/*  @Override
  public UserDto retrieveUserDto(String username) {
    return UserMapper.toDto(dao.getById(username));
  }

  @Override
  public Boolean createUser(UserDto userDto) {

    if (dao.getById(userDto.getUsername()) != null)
      return false;
    userDto.setPassword(BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt(10)));
    dao.create(UserMapper.toPojo(userDto));
    return true;
  }

  @Override
  public Boolean deleteUser(String username) {
    if (dao.getById(username) == null)
      return false;
    dao.deleteById(username);
    return true;
  }*/

  @Override
  public Page<User> getAllUsers(Pageable pageable) {
    Page<User> users = userDao.findAllBy(pageable);
    return users;
  }

}
