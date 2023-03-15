package sg.edu.nus.iss.day24_lecture.exceptions;

public class BookException extends RuntimeException {
    
    public BookException() {
        super();
    }

    public BookException(String message) {
        super();
    }

    public BookException(String message, Throwable cause) {
        super(message, cause);
    }
}
