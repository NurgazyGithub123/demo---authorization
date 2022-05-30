package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.CodeEnterAttemptDto;
import kg.itschool.demoauthorization.models.dtos.RoleDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CodeEnterAttemptService {
    CodeEnterAttemptDto save(CodeEnterAttemptDto codeEnterAttemptDto);

    List<CodeEnterAttemptDto> findAllByUserCode(UserCodeDto userCodeDto);
}
