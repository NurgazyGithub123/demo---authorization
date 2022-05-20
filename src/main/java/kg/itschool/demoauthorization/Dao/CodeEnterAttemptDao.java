package kg.itschool.demoauthorization.Dao;

import kg.itschool.demoauthorization.models.dtos.CodeEnterAttemptDto;
import kg.itschool.demoauthorization.models.entitys.CodeEnterAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeEnterAttemptDao extends JpaRepository<CodeEnterAttempt, Long>{
}
