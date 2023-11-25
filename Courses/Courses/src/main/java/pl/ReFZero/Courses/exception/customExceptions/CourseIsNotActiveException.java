package pl.ReFZero.Courses.exception.customExceptions;

public class CourseIsNotActiveException extends RuntimeException {
    private static final long serialVersionUID = 5;

    public CourseIsNotActiveException(String message) {
        super(message);
    }
}
