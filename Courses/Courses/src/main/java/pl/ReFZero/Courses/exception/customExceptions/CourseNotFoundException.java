package pl.ReFZero.Courses.exception.customExceptions;

public class CourseNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public CourseNotFoundException(String message){
        super(message);
    }
}
