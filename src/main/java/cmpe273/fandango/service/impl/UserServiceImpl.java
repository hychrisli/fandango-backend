package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.mapper.UserMapper;
import cmpe273.fandango.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  private UserMapper userMapper = new UserMapper();

  @Override
  public Boolean createUser(UserDto userDto) {
    return null;
  }

  @Override
  public Boolean deleteUser(Integer userId) {
    return null;
  }

  @Override
  public UserDto updateUser(Integer userId, UserDto userDto) {
    User user = userDao.findUserByUserId(userId);
    if (user != null){
      user = userMapper.updPojo(userDto, user);
      userDao.save(user);
      return userMapper.toDto(user);
    }
    return null;
  }

  @Override
  public UserDto getUser(Integer userId) {
    return userMapper.toDto(userDao.findUserByUserId(userId));
  }

  @Override
  public Page<UserSimpleDto> getAllUsers(Pageable pageable) {
    Page<User> users = userDao.findAllBy(pageable);
    return new PageImpl<>(
        StreamSupport.stream(users.spliterator(), false)
        .map(userMapper::toSimpleDto).collect(Collectors.toList()));
  }
}
