package kg.itschool.demoauthorization.mappers;

import kg.itschool.demoauthorization.models.dtos.RoleDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.entitys.Role;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserCodeMapper extends BaseMapper<UserCode, UserCodeDto>{

     UserCodeMapper INSTANCE = Mappers.getMapper(UserCodeMapper.class);
}
