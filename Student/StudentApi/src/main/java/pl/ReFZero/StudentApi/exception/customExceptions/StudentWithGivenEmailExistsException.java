package pl.ReFZero.StudentApi.exception.customExceptions;

public class StudentWithGivenEmailExistsException extends RuntimeException{

    private static final long serialVersionUID = 2;

    public StudentWithGivenEmailExistsException(String message){
        super(message);
    }
}
