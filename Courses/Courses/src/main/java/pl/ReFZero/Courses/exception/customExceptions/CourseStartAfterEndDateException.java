package pl.ReFZero.Courses.exception.customExceptions;


public class CourseStartAfterEndDateException  extends RuntimeException{

    private static final long serialVersionUID = 2;

    public CourseStartAfterEndDateException(String message){
        super(message);
    }
}

