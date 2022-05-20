package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;

public interface UserCodeService {

    UserCodeDto save(UserCodeDto userCodeDto);

    UserCodeDto findByUser(UserDto userDto);
}
