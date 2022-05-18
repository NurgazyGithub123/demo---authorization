package kg.itschool.demoauthorization.Dao;

import kg.itschool.demoauthorization.models.entitys.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
}
