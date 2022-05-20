package kg.itschool.demoauthorization.models.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    Date sentDate;

    private boolean confirm;


}
