package kg.itschool.demoauthorization.services.impl;


import kg.itschool.demoauthorization.Dao.UserCodeDao;
import kg.itschool.demoauthorization.mappers.UserCodeMapper;
import kg.itschool.demoauthorization.mappers.UserMapper;
import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;
import kg.itschool.demoauthorization.models.entitys.Account;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import kg.itschool.demoauthorization.services.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class UserCodeServiceImpl implements UserCodeService {

    @Autowired
    private UserCodeDao userCodeDao;
    private UserCodeMapper userCodeMapper = UserCodeMapper.INSTANCE;

    private UserMapper userMapper = UserMapper.INSTANCE;


    @Override
    public UserCodeDto save(UserCodeDto userCodeDto) {
        UserCode userCode = userCodeMapper.toEntity(userCodeDto);
        return userCodeMapper.toDto(userCodeDao.save(userCode));
    }

    @Override
    public UserCodeDto findByUserAndExtDate(UserDto userDto, Date date) {
        UserCode userCode = userCodeDao.findByUserAndExpirationDateAfter(userMapper.toEntity(userDto), date);
        return userCodeMapper.toDto(userCode);
    }


}
