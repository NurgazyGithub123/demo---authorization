package kg.itschool.demoauthorization.services.impl;

import kg.itschool.demoauthorization.Dao.AccountDao;
import kg.itschool.demoauthorization.mappers.AccountMapper;
import kg.itschool.demoauthorization.mappers.UserMapper;
import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;
import kg.itschool.demoauthorization.models.entitys.Account;
import kg.itschool.demoauthorization.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    private AccountMapper accountMapper = AccountMapper.INSTANCE;
    private UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public AccountDto save(AccountDto accountDto) {
        Account account = accountMapper.toEntity(accountDto);
        return accountMapper.toDto(accountDao.save(account));
    }

    @Override
    public AccountDto findByUser(UserDto userDto) {
        Account account = accountDao.findByUser(userMapper.toEntity(userDto));
        return accountMapper.toDto(account);
    }

    @Override
    public AccountDto findByEmail(String email) {
        Account account = accountDao.findByLogin(email);
        return accountMapper.toDto(account);
    }
}
