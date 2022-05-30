package kg.itschool.demoauthorization.services.impl;

import kg.itschool.demoauthorization.configuration.EmailSender;
import kg.itschool.demoauthorization.exceptions.SignInErrorException;
import kg.itschool.demoauthorization.models.dtos.*;
import kg.itschool.demoauthorization.models.enums.Status;
import kg.itschool.demoauthorization.models.request.SignInRequest;
import kg.itschool.demoauthorization.models.response.Message;
import kg.itschool.demoauthorization.services.*;
import kg.itschool.demoauthorization.utils.ConfirmCodeGenerator;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.*;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired private UserService userService;
    @Autowired private AccountService accountService;
    @Autowired private RoleService roleService;
    @Autowired private EmailSender emailSender;
    @Autowired private ConfirmCodeGenerator confirmCodeGenerator;
    @Autowired private UserCodeService userCodeService;
    @Autowired private CodeEnterAttemptService attemptService;
    @Autowired private PasswordEncoder encoder;

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
                    .password(encoder.encode(signInRequest.getPassword())) // кодировка пароля
                    .user(userDto)
                    .build());
            System.out.println("OK");
            //вызвать метод sendCode, он вернет код, который был сгенерирован и отправлен на почту
            ResponseEntity<?> codeSendResponce = sendCode(userDto);

            if(!codeSendResponce.getStatusCode().equals(HttpStatus.OK)) return codeSendResponce;
            //Надо сохранить этот код в нашей базе. Для этого создаем новый объект UserCode

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
    public ResponseEntity<?> sendCode(UserDto userDto) {

        String code = confirmCodeGenerator.generatePassword(4);
        //generate random code - рандомно сгеренрировать 4-х значное число
        try {
            emailSender.sendSimpleMessage(userDto.getEmail(), "Код подтверждения", "Ваш код подтверждения: " + code);
        }catch (SignInErrorException ex){
            // вернуть ответ с ошибкой, что произошла ошибка в процессе отправки кода
            return new ResponseEntity<>(Message.of("произошла ошибка в процессе отправки кода"), HttpStatus.NOT_ACCEPTABLE);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 3);

        UserCodeDto userCodeDto = UserCodeDto
                .builder()
                .user(userDto)
                .code(code)
                .sentDate(new Date())
                .expirationDate(calendar.getTime())
                .confirm(false)
                .build();
        // заполняем его поля
        userCodeService.save(userCodeDto);
        //return code;
        return new ResponseEntity<>(code, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> confirmation(String email, String code) {
        // найти User по email
        UserDto userDto = userService.findByEmail(email);
        // найти UserCode по User
        UserCodeDto userCodeDto = userCodeService.findByUserAndExtDate(userDto, new Date());

        if(userCodeDto == null){
            return new ResponseEntity<>(Message.of("Срок дейсвия кода истек"), HttpStatus.NOT_ACCEPTABLE);
        }
        // сравнить код из UserCode с code

        List<CodeEnterAttemptDto> attemptDtos = attemptService.findAllByUserCode(userCodeDto);
        if(attemptDtos.size() < 3){

            if(userCodeDto.getCode().equals(code)){

            CodeEnterAttemptDto codeEnterAttemptDto = CodeEnterAttemptDto
                    .builder()
                    .status(Status.SUCCESSFUL)
                    .attemptDate(new Date())
                    .userCode(userCodeDto)
                    .build();
            attemptService.save(codeEnterAttemptDto);

             userCodeDto.setConfirm(true);
             userCodeService.save(userCodeDto);

             userDto.setConfirm(true);
             userService.save(userDto);

            return new ResponseEntity<>(Message.of("Код подтвержден, добро пожаловать в систему"), HttpStatus.OK);
        }else
            {
                CodeEnterAttemptDto codeEnterAttemptDto = CodeEnterAttemptDto.builder()
                        .userCode(userCodeDto)
                        .attemptDate(new Date())
                        .status(Status.FAILED)
                        .build();
                attemptService.save(codeEnterAttemptDto);
                return new ResponseEntity<>(Message.of("Вы не правильно ввели код. Попробуйте еще раз"), HttpStatus.NOT_ACCEPTABLE);
            }
        }else
            {
            userCodeDto.setConfirm(true);
            userCodeService.save(userCodeDto);

            userDto.setConfirm(true);
            userService.save(userDto);
            return new ResponseEntity<>(Message.of("Количество попыток ввода кода исчерпано!"), HttpStatus.NOT_ACCEPTABLE);
            }
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
