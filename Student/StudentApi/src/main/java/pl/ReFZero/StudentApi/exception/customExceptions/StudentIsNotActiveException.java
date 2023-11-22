package pl.ReFZero.StudentApi.exception.customExceptions;

public class StudentIsNotActiveException extends Exception{
    private static final long serialVersionUID = 3;

    public StudentIsNotActiveException(String message){
        super(message);
    }
}
