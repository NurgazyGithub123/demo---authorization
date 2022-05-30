package kg.itschool.demoauthorization.models.dtos;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
public class AccountDto {

    private Long id;
    private String login;
    private String password;
    private UserDto user;
}
