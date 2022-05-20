package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;

public interface UserService {

    UserDto save(UserDto userDto);
    UserDto findById(Long id);

}
