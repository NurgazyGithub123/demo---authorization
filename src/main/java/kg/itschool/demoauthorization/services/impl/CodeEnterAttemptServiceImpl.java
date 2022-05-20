package kg.itschool.demoauthorization.services.impl;

import kg.itschool.demoauthorization.Dao.CodeEnterAttemptDao;
import kg.itschool.demoauthorization.mappers.CodeEnterAttemptMapper;
import kg.itschool.demoauthorization.mappers.UserCodeMapper;
import kg.itschool.demoauthorization.models.dtos.CodeEnterAttemptDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.entitys.CodeEnterAttempt;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import kg.itschool.demoauthorization.services.CodeEnterAttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodeEnterAttemptServiceImpl implements CodeEnterAttemptService {

    @Autowired
    private CodeEnterAttemptDao codeEnterAttemptDao;
    private CodeEnterAttemptMapper codeEnterAttemptMapper = CodeEnterAttemptMapper.INSTANCE;
    private UserCodeMapper userCodeMapper = UserCodeMapper.INSTANCE;


}
