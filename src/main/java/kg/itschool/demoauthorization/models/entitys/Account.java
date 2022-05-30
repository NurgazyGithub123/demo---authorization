package kg.itschool.demoauthorization.models.entitys;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tb_account")
public class Account {

    @Id
    @GeneratedValue
    private Long id;
    private String login;
    private String password;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
