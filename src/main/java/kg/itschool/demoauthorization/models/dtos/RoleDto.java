package kg.itschool.demoauthorization.models.dtos;

import kg.itschool.demoauthorization.models.enums.RoleType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleDto {

    Long id;
    RoleType role;
}
