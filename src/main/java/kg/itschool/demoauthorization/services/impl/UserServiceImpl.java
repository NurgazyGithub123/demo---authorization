package kg.itschool.demoauthorization.services.impl;

import kg.itschool.demoauthorization.Dao.UserDao;
import kg.itschool.demoauthorization.mappers.UserMapper;
import kg.itschool.demoauthorization.models.dtos.UserDto;
import kg.itschool.demoauthorization.models.entitys.User;
import kg.itschool.demoauthorization.services.UserService;
import kg.itschool.demoauthorization.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    private UserMapper userMapper = UserMapper.INSTANCE;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userMapper.toEntity(userDto);
        return userMapper.toDto(userDao.save(user));
    }

    @Override
    public UserDto findById(Long id) {
        User user = userDao.findById(id).orElse(null);
        return userMapper.toDto(user);
    }

    @Override
    public UserDto findByEmail(String email) {
        User user = userDao.findByEmail(email);
        return userMapper.toDto(user);
    }

    @Override
    public UserDetails loadUserByUserName(String name) {
        User user = userDao.findByName(name);
        if(Objects.isNull(user)) throw new RuntimeException();

        return UserDetailsImpl.build(user);
    }
}
