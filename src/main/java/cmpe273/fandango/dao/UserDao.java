package cmpe273.fandango.dao;

import cmpe273.fandango.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDao extends PagingAndSortingRepository<User, Integer>{

  Page<User> findAllBy(Pageable pageable);

  User findUserByUserId(Integer userId);

  User findUserByUsername(String username);

}