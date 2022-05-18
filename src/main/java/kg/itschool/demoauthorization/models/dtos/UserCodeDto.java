package kg.itschool.demoauthorization.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserCodeDto {

    Long id;
    UserDto user;
    String code;
    Date sentDate;
}
