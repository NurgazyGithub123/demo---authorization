package kg.itschool.demoauthorization.models.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String phone;
    private String email;
    private RoleDto role;
    private boolean confirm;
}
