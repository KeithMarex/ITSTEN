package nl.duckstudios.pintandpillage.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String message) {
        super("Email adres is taken " + message);
    }
}
