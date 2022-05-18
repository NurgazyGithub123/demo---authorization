package kg.itschool.demoauthorization.controller;

import kg.itschool.demoauthorization.models.request.SignInRequest;
import kg.itschool.demoauthorization.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return authService.signIn(signInRequest);
    }

    @GetMapping("/logIn")
    public ResponseEntity<?> logIn(@RequestParam String login, @RequestParam String password){
        return authService.logIn(login, password);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam  String email, @RequestParam String code){
        return authService.confirmation(email, code);
    }
}
