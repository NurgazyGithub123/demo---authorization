package kg.itschool.demoauthorization.services.impl;

import kg.itschool.demoauthorization.Dao.RoleDao;
import kg.itschool.demoauthorization.mappers.RoleMapper;
import kg.itschool.demoauthorization.models.dtos.RoleDto;
import kg.itschool.demoauthorization.models.entitys.Role;
import kg.itschool.demoauthorization.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;
    private RoleMapper roleMapper = RoleMapper.INSTANCE;

    @Override
    public RoleDto save(RoleDto roleDto) {
        Role role = roleMapper.toEntity(roleDto);
        return roleMapper.toDto(roleDao.save(role));
    }
}
