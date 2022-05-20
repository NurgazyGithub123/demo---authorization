package kg.itschool.demoauthorization.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
public class UserCodeDto {

    private Long id;
    private UserDto user;
    private String code;
    private Date sentDate;
    private boolean confirm;
}
