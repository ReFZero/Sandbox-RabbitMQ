package pl.ReFZero.Courses.exception.customExceptions;

public class CourseStudentIsNotActiveException extends RuntimeException{

    private static final long serialVersionUID = 6;

    public CourseStudentIsNotActiveException(String message){
        super(message);
    }
}
