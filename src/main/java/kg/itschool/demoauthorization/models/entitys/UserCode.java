package kg.itschool.demoauthorization.models.entitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

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
    private User user;
    private String code;
    private Date sentDate;
    @Column
    private Date expirationDate;
    private boolean confirm;


}
