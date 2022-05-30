package kg.itschool.demoauthorization.models.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtResponse {

    String jwt;
    Long userid;
    String name;
    String email;
    List<String> roles;
}


