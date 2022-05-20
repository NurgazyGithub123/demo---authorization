package kg.itschool.demoauthorization.services.impl;


import kg.itschool.demoauthorization.configuration.EmailSender;
import kg.itschool.demoauthorization.exceptions.SignInErrorException;
import kg.itschool.demoauthorization.models.dtos.AccountDto;
import kg.itschool.demoauthorization.models.dtos.RoleDto;
import kg.itschool.demoauthorization.models.dtos.UserCodeDto;
import kg.itschool.demoauthorization.models.dtos.UserDto;
import kg.itschool.demoauthorization.models.entitys.Account;
import kg.itschool.demoauthorization.models.entitys.User;
import kg.itschool.demoauthorization.models.entitys.UserCode;
import kg.itschool.demoauthorization.models.request.SignInRequest;
import kg.itschool.demoauthorization.models.response.Message;
import kg.itschool.demoauthorization.services.*;
import kg.itschool.demoauthorization.utils.ConfirmCodeGenerator;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserService userService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private EmailSender emailSender;

    @Autowired
    private ConfirmCodeGenerator confirmCodeGenerator;
    @Autowired
    private UserCodeService userCodeService;



    @Override
    public ResponseEntity<?> signIn(SignInRequest signInRequest) {
        AccountDto accountDto = accountService.findByEmail(signInRequest.getEmail());
        System.out.println("CHECK");
        if(accountDto != null){
            System.out.println("TEST");
            return new ResponseEntity<>(Message.of("Пользователь с данным email уже зарегистрирован"), HttpStatus.NOT_ACCEPTABLE);
        }

        String violationResponse = validateConstraints(signInRequest);
        if(violationResponse != null){
            return new ResponseEntity<>(Message.of(violationResponse), HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            RoleDto role = RoleDto.builder().role(signInRequest.getRoleType()).build();
            role = roleService.save(role);

            UserDto userDto = userService.save(UserDto.builder()
                    .email(signInRequest.getEmail())
                    .name(signInRequest.getName())
                    .phone(signInRequest.getPhone())
                    .role(role)
                    .confirm(false)
                    .build());

            accountService.save(AccountDto.builder()
                    .login(signInRequest.getEmail())
                    .password(signInRequest.getPassword())
                    .user(userDto)
                    .build());
            System.out.println("OK");
            //вызвать метод sendCode, он вернет код, который был сгенерирован и отправлен на почту
            ResponseEntity<?> codeSendResponce = sendCode(signInRequest.getEmail());

            if(!codeSendResponce.getStatusCode().equals(HttpStatus.OK)) return codeSendResponce;
            //Надо сохранить этот код в нашей базе. Для этого создаем новый объект UserCode

            UserCodeDto userCodeDto = UserCodeDto
                    .builder()
                    .user(userDto)
                    .code(codeSendResponce.getBody().toString())
                    .sentDate(new Date())
                    .confirm(false)
                    .build();
            // заполняем его поля
            userCodeService.save(userCodeDto);
            System.out.println("сохранен код в базе");

            //save UserCode - сохраняем
            return new ResponseEntity<>(Message.of("Вам отправлен код подтверждения"), HttpStatus.OK);


        }catch (Exception ex){
            return new ResponseEntity<>(Message.of(ex.getMessage()), HttpStatus.NOT_ACCEPTABLE);
            // throw new SignInErrorException(ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<?> logIn(String login, String password) {
        AccountDto accountDto = accountService.findByEmail(login);
        if(accountDto == null){
            return new ResponseEntity<>(Message.of("Не верный логин"), HttpStatus.NOT_ACCEPTABLE);
        }else if(!accountDto.getPassword().equals(password)){
            return new ResponseEntity<>(Message.of("Не верный пароль"), HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(Message.of("Добро пожаловать в систему"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> sendCode(String email) {

        String code = confirmCodeGenerator.generatePassword(4);
        //generate random code - рандомно сгеренрировать 4-х значное число
        try {
            emailSender.sendSimpleMessage(email, "Код подтверждения", "Ваш код подтверждения: " + code);
        }catch (SignInErrorException ex){
            // вернуть ответ с ошибкой, что произошла ошибка в процессе отправки кода
            return new ResponseEntity<>(Message.of("произошла ошибка в процессе отправки кода"), HttpStatus.NOT_ACCEPTABLE);
        }
        //return code;
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirmation(String email, String code) {
        // найти User по email
        AccountDto accountDto = accountService.findByEmail(email);
        UserDto userDto = accountDto.getUser();

        // найти UserCode по User
        UserCodeDto userCodeDto = userCodeService.findByUser(userDto);
        // сравнить код из UserCode с code
        if(userCodeDto.getCode().equals(code)){
            // если совпадают подтверждаем и в UserCode апдейтим поле confirm = true
            System.out.print("Код подтвержден: " + code);

             userCodeDto.setConfirm(true);
             userCodeService.save(userCodeDto);
            return new ResponseEntity<>(Message.of("Добро пожаловать в систему"), HttpStatus.OK);
        }
        // если нет, возвращаем ответ, что введен неверный код
        return new ResponseEntity<>(Message.of("Не верный код"), HttpStatus.NOT_ACCEPTABLE);
    }

    private <T> String validateConstraints(T object) {
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);

        if (constraintViolations.size() > 0) {
            Set<String> violationMessages = new HashSet<String>();

            for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                violationMessages.add(constraintViolation.getPropertyPath() + ": " + constraintViolation.getMessage());
            }

            return "Неверные данные:\n" + StringUtils.join(violationMessages, '\n');

        }

        return null;
    }

}
