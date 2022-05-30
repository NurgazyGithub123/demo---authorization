package kg.itschool.demoauthorization.models.entitys;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String phone;
    @Email(message = "Email is not valid")
    private String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @Column
    private boolean confirm;
}
