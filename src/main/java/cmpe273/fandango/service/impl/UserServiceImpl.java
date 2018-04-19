package cmpe273.fandango.service.impl;

import cmpe273.fandango.dao.UserDao;
import cmpe273.fandango.dto.UserSimpleDto;
import cmpe273.fandango.entity.User;
import cmpe273.fandango.mapper.UserMapper;
import cmpe273.fandango.service.UserService;
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
  UserDao userDao;

  @Override
  public Boolean createUser(User user) {
    return null;
  }

  @Override
  public Boolean deleteUser(Integer userId) {
    return null;
  }

  @Override
  public User updateUser(User user) {
    return null;
  }

  @Override
  public User getUser(Integer userId) {
    return userDao.findUserByUserId(userId);
  }

  @Override
  public Page<UserSimpleDto> getAllUsers(Pageable pageable) {
    Page<User> users = userDao.findAllBy(pageable);
    return new PageImpl<>(
        StreamSupport.stream(users.spliterator(), false)
        .map(UserMapper::toSimpleDto).collect(Collectors.toList()));
  }
}
