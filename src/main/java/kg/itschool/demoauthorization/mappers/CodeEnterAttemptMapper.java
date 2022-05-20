package kg.itschool.demoauthorization.mappers;

import kg.itschool.demoauthorization.models.dtos.CodeEnterAttemptDto;
import kg.itschool.demoauthorization.models.entitys.CodeEnterAttempt;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CodeEnterAttemptMapper extends BaseMapper<CodeEnterAttempt, CodeEnterAttemptDto>{

    CodeEnterAttemptMapper INSTANCE = Mappers.getMapper(CodeEnterAttemptMapper.class);
}
