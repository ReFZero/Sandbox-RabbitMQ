package pl.ReFZero.Courses.exception;

import org.apache.commons.configuration.ConfigurationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pl.ReFZero.Courses.exception.customExceptions.CourseCanNotSetFullStatusException;
import pl.ReFZero.Courses.exception.customExceptions.CourseNotFoundException;
import pl.ReFZero.Courses.exception.customExceptions.CourseParticipantsLimitIsExceededException;
import pl.ReFZero.Courses.exception.customExceptions.CourseStartAfterEndDateException;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConfigurationException.class)
    public ResponseEntity<ErrorObject> studentNotFoundExceptionHandler(CourseNotFoundException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseStartAfterEndDateException.class)
    public ResponseEntity<ErrorObject> courseStartAfterEndDateExceptionHandler(CourseStartAfterEndDateException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseParticipantsLimitIsExceededException.class)
    public ResponseEntity<ErrorObject> courseParticipantsLimitIsExceededExceptionHandler(CourseParticipantsLimitIsExceededException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseCanNotSetFullStatusException.class)
    public ResponseEntity<ErrorObject> courseCanNotSetFullStatusExceptionHandler(CourseCanNotSetFullStatusException ex, WebRequest request) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(HttpStatus.CONFLICT.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, HttpStatus.CONFLICT);
    }


}
