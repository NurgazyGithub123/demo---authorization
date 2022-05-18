package kg.itschool.demoauthorization.Dao;

import kg.itschool.demoauthorization.models.entitys.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCodeDao extends JpaRepository<UserCode, Long> {
}
