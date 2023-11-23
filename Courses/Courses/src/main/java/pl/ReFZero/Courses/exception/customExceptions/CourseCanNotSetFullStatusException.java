package pl.ReFZero.Courses.exception.customExceptions;

public class CourseCanNotSetFullStatusException extends RuntimeException{
    private static final long serialVersionUID = 4;

    public CourseCanNotSetFullStatusException(String message) {
        super(message);
    }
}

