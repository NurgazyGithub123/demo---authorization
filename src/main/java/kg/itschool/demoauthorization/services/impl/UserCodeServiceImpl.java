package kg.itschool.demoauthorization.services.impl;


import kg.itschool.demoauthorization.Dao.UserCodeDao;
import kg.itschool.demoauthorization.mappers.UserCodeMapper;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import kg.itschool.demoauthorization.services.UserCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCodeServiceImpl implements UserCodeService {

    @Autowired
    private UserCodeDao userCodeDao;
    private UserCodeMapper userCodeMapper = UserCodeMapper.INSTANCE;


    @Override
    public UserCodeDto save(UserCodeDto userCodeDto) {
        UserCode userCode = userCodeMapper.toEntity(userCodeDto);
        return userCodeMapper.toDto(userCodeDao.save(userCode));
    }
}
