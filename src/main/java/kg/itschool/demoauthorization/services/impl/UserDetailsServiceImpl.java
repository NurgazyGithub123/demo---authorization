package kg.itschool.demoauthorization.services.impl;

import kg.itschool.demoauthorization.Dao.UserDao;
import kg.itschool.demoauthorization.models.entitys.User;
import kg.itschool.demoauthorization.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username);
        if(Objects.isNull(user)) throw new RuntimeException("User not found");
        return UserDetailsImpl.build(user);
    }
}
