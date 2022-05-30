package kg.itschool.demoauthorization.Dao;

import kg.itschool.demoauthorization.models.entitys.Account;
import kg.itschool.demoauthorization.models.entitys.User;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;

@Repository
public interface UserCodeDao extends JpaRepository<UserCode, Long> {

    UserCode findByUserAndExpirationDateAfter(User user, Date date);
}
