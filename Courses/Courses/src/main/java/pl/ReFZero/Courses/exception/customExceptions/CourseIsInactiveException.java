package pl.ReFZero.Courses.exception.customExceptions;

public class CourseIsInactiveException extends RuntimeException{
    private static final long serialVersionUID = 9;

    public CourseIsInactiveException(String message) {
        super(message);
    }
}
