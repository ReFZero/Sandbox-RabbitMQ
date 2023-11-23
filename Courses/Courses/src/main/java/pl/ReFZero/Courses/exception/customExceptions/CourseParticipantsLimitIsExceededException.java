package pl.ReFZero.Courses.exception.customExceptions;

public class CourseParticipantsLimitIsExceededException extends RuntimeException{
    private static final long serialVersionUID = 3;

    public CourseParticipantsLimitIsExceededException(String message) {
        super(message);
    }
}

