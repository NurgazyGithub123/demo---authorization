package kg.itschool.demoauthorization.services;

import kg.itschool.demoauthorization.models.request.SignInRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    ResponseEntity<?> signIn(SignInRequest signInRequest);

    ResponseEntity<?> logIn(String login, String password);

    String sendCode(String email);

    ResponseEntity<?> confirmation(String email, String code);
}
