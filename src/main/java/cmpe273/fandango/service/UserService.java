package cmpe273.fandango.service;

import cmpe273.fandango.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    
    //public UserDto retrieveUserDto(String username);
    
    //public Boolean createUser(UserDto userDto);
    
    //public Boolean deleteUser(String username);

    Page<User> getAllUsers(Pageable pageable);
}
