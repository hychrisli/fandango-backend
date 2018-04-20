package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.dto.LoginDto;
import cmpe273.fandango.dto.UserCreateDto;
import cmpe273.fandango.dto.UserDto;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.mapper.UserMapper;
import cmpe273.fandango.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserDao userDao;

  @Autowired
  private PasswordEncoder passwordEncoder;

  private UserMapper userMapper = new UserMapper();

  @Override
  public UserDto createUser(UserCreateDto userCreateDto) {
    userCreateDto.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
    User user = userMapper.toPojo(userCreateDto);
    user = userDao.save(user);
    return userMapper.toDto(user);
  }

  @Override
  public Boolean deleteUser(Integer userId) {
    if (userDao.findUserByUserId(userId) == null)
      return false;
    userDao.delete(userId);
    return true;
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
  public User getUserByUsername(String username) {
    return userDao.findUserByUsername(username);
  }

  @Override
  public UserDto loginUser(LoginDto loginDto) {
    User user = userDao.findUserByUsername(loginDto.getUsername());
    if ( user == null )
      return null;
    if(passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))
      return userMapper.toDto(user);
    else
      return null;
  }

  @Override
  public Page<UserSimpleDto> getAllUsers(Pageable pageable) {
    Page<User> users = userDao.findAllBy(pageable);
    return new PageImpl<>(
        StreamSupport.stream(users.spliterator(), false)
        .map( userMapper::toSimpleDto).collect(Collectors.toList()));
  }
}
