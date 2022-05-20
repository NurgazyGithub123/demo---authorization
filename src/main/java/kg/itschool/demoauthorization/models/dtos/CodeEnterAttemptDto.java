package kg.itschool.demoauthorization.models.dtos;

import kg.itschool.demoauthorization.models.entitys.UserCode;
import kg.itschool.demoauthorization.models.enums.Status;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CodeEnterAttemptDto {

    private Long id;
    private Date attemptDate;
    private UserCode userCode;
    private Status status;
}
