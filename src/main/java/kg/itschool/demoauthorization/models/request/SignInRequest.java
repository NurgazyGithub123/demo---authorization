package kg.itschool.demoauthorization.models.request;

import kg.itschool.demoauthorization.models.enums.RoleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignInRequest {

    @NotEmpty(message = "Имя не должно быть пустым")
    String name;

    String phone;

    @Email(message = "Неверный формат email")
    @NotEmpty
    String email;

    @Size(min = 5, max = 8, message = "Пароль должен быть не менее 5 и не более 8 символов")
    @NotEmpty
    String password;
    RoleType roleType;

}
