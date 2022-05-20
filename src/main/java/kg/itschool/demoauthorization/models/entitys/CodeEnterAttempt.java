package kg.itschool.demoauthorization.models.entitys;

import kg.itschool.demoauthorization.models.enums.Status;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tb_attempts")
@Data
public class CodeEnterAttempt {

    @Id
    @GeneratedValue
    private Long id;
    private Date attemptDate;
    @ManyToOne
    @JoinColumn(name = "user_code_id")
    private UserCode userCode;
    private Status status;
}
