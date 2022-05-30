package kg.itschool.demoauthorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/all")
    public String access(){
        return "All access";
    }
    //@PreAuthorize -- проверка перед авторизации, на роли
    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or hasRole('USER') or hasRole('MODERATOR')")
    public String userAccess(){
        return "USER Access";
    }

    @GetMapping("/moderator")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess(){
        return "MODERATOR Access";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess(){
        return "ADMIN Access";
    }
}
