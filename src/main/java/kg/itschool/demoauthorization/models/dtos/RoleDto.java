package kg.itschool.demoauthorization.models.dtos;

import kg.itschool.demoauthorization.models.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
public class RoleDto {

    private Long id;
    private RoleType role;
}
