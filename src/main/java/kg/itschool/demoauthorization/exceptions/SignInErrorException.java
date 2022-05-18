package kg.itschool.demoauthorization.exceptions;

public class SignInErrorException extends RuntimeException{

    public SignInErrorException(String message){
        super(message);
    }
}
