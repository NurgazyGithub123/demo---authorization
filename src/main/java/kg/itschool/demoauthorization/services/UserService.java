package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {

    UserDto save(UserDto userDto);
    UserDto findById(Long id);

    UserDto findByEmail(String email);

    UserDetails loadUserByUserName(String name);
}
