package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;

public interface AccountService {
    AccountDto save(AccountDto accountDto);
    AccountDto findByUser(UserDto userDto);
    AccountDto findByEmail(String email);

}
