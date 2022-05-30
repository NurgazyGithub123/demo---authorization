package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;

import javax.xml.crypto.Data;
import java.util.Date;

public interface UserCodeService {

    UserCodeDto save(UserCodeDto userCodeDto);

    UserCodeDto findByUserAndExtDate(UserDto userDto, Date date);
}
