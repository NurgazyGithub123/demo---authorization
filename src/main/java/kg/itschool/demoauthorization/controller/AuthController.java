package kg.itschool.demoauthorization.controller;

import kg.itschool.demoauthorization.models.request.LoginRequest;
import kg.itschool.demoauthorization.models.request.SignInRequest;
import kg.itschool.demoauthorization.models.response.JwtResponse;
import kg.itschool.demoauthorization.services.AuthService;
import kg.itschool.demoauthorization.utils.JwtUtils;
import kg.itschool.demoauthorization.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;


   // регистрация
    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody SignInRequest signInRequest){
        return authService.signIn(signInRequest);
    }
    // войти в систему
    @PostMapping("/logIn") // logIn --> должен вернуть name, email, role, jwt токен
    public ResponseEntity<?> logIn(@RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate( //authenticationManager создает обьект authentication
                new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword())); // принимает new UsernamePasswordAuthenticationToken
        SecurityContextHolder.getContext().setAuthentication(authentication);//SecurityContextHolder можем полкчить пользователя
        String jwt = jwtUtils.generateToken(authentication);// получаем токен
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return new ResponseEntity<>(JwtResponse.builder()
                .jwt(jwt) // вернем полученный токен
                .name(userDetails.getUsername())
                .email(userDetails.getUsername())
                .roles(roles)
                .build(),
                HttpStatus.NOT_ACCEPTABLE);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> confirm(@RequestParam  String email, @RequestParam String code){
        return authService.confirmation(email, code);
    }
}
