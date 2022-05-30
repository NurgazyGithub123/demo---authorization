package kg.itschool.demoauthorization.models.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LoginRequest {
    @NotEmpty(message = "Имя не должно быть пустым")
    @Email(message = "Неверный формат login")
    String login;

    @Size(min = 5, max = 8, message = "Пароль должен быть не менее 5 и не более 8 символов")
    @NotEmpty
    String password;
}
