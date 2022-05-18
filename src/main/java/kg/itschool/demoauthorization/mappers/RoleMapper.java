package kg.itschool.demoauthorization.mappers;

import kg.itschool.demoauthorization.models.dtos.RoleDto;
import kg.itschool.demoauthorization.models.entitys.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper extends BaseMapper<Role, RoleDto> {

    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);
}
