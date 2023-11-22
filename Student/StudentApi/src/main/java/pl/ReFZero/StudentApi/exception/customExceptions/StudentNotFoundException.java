package pl.ReFZero.StudentApi.exception.customExceptions;

public class StudentNotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1;

    public StudentNotFoundException(String message){
        super(message);
    }
}
