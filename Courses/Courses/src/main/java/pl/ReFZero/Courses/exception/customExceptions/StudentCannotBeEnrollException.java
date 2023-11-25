package pl.ReFZero.Courses.exception.customExceptions;

public class StudentCannotBeEnrollException extends RuntimeException{
    private static final long serialVersionUID = 8;

    public StudentCannotBeEnrollException(String message) {
        super(message);
    }
}
