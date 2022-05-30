package kg.itschool.demoauthorization.Dao;

import kg.itschool.demoauthorization.models.entitys.CodeEnterAttempt;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CodeEnterAttemptDao extends JpaRepository<CodeEnterAttempt, Long>{

    List<CodeEnterAttempt> findAllByUserCode(UserCode userCode);
}
