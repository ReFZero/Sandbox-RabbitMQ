package pl.ReFZero.Courses.exception;

import feign.FeignException;
import org.apache.commons.configuration.ConfigurationException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import pl.ReFZero.Courses.exception.customExceptions.*;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConfigurationException.class)
    public ResponseEntity<ErrorObject> studentNotFoundExceptionHandler(CourseNotFoundException ex, WebRequest request) {
        return getException(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CourseStartAfterEndDateException.class)
    public ResponseEntity<ErrorObject> courseStartAfterEndDateExceptionHandler(CourseStartAfterEndDateException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseParticipantsLimitIsExceededException.class)
    public ResponseEntity<ErrorObject> courseParticipantsLimitIsExceededExceptionHandler(CourseParticipantsLimitIsExceededException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseCanNotSetFullStatusException.class)
    public ResponseEntity<ErrorObject> courseCanNotSetFullStatusExceptionHandler(CourseCanNotSetFullStatusException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseIsNotActiveException.class)
    public ResponseEntity<ErrorObject> courseIsNotActiveExceptionHandler(CourseIsNotActiveException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseStudentIsNotActiveException.class)
    public ResponseEntity<ErrorObject> courseStudentIsNotActiveExceptionHandler(CourseStudentIsNotActiveException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(CourseStudentIAAlreadyEnrolledException.class)
    public ResponseEntity<ErrorObject> courseStudentIAAlreadyEnrolledExceptionHandler(CourseStudentIAAlreadyEnrolledException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(StudentCannotBeEnrollException.class)
    public ResponseEntity<ErrorObject> studentCannotBeEnrollExceptionHandler(StudentCannotBeEnrollException ex, WebRequest request) {
        return getException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CourseIsInactiveException.class)
    public ResponseEntity<ErrorObject> courseIsInactiveExceptionHandler(CourseIsInactiveException ex, WebRequest request) {
        return getException(ex, HttpStatus.CONFLICT);
    }
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<?> handleFeignException(FeignException e){
      return ResponseEntity.status(e.status()).body( new JSONObject(e.contentUTF8()).toMap());
    }
    private static <T extends RuntimeException> ResponseEntity<ErrorObject> getException(T ex, HttpStatus httpStatus) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(httpStatus.value());
        errorObject.setMessage(ex.getMessage());
        errorObject.setTimestamp(new Date());

        return new ResponseEntity<>(errorObject, httpStatus);
    }



}
