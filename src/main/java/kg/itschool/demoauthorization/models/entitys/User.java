package kg.itschool.demoauthorization.models.entitys;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Data
@Entity
@Table(name = "tb_user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {

    @Id
    @GeneratedValue
    Long id;
    String name;
    String phone;
    @Email(message = "Email is not valid")
    String email;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Role role;
    @Column
    boolean confirm;
}
