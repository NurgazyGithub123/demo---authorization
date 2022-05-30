package kg.itschool.demoauthorization.models.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date sentDate;
    @JsonFormat(pattern = "dd.MM.yyyy HH:mm:ss")
    private Date expirationDate;
    private boolean confirm;
}
