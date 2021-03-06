package kg.itschool.demoauthorization.mappers;

import kg.itschool.demoauthorization.models.dtos.UserDto;
import kg.itschool.demoauthorization.models.entitys.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface UserMapper extends BaseMapper<User, UserDto>{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
}
