package kg.itschool.demoauthorization.models.entitys;

import kg.itschool.demoauthorization.models.enums.RoleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
    @GeneratedValue
    private Long id;
    private RoleType role;
}
