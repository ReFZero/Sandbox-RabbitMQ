package pl.ReFZero.Courses.exception.customExceptions;

public class CourseStudentIAAlreadyEnrolledException extends RuntimeException{
    private static final long serialVersionUID = 7;

    public CourseStudentIAAlreadyEnrolledException(String message) {
        super(message);
    }
}
