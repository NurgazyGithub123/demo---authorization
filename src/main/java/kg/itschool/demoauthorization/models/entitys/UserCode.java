package kg.itschool.demoauthorization.models.entitys;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_user_code")
public class UserCode {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    User user;
    String code;
    Date sentDate;


}
