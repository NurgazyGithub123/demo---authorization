package kg.itschool.demoauthorization.models.entitys;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_account")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Account {

    @Id
    @GeneratedValue
    Long id;
    String login;
    String password;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
