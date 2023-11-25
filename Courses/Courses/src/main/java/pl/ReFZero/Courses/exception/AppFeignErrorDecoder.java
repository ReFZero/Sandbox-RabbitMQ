package pl.ReFZero.Courses.exception;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import pl.ReFZero.Courses.exception.customExceptions.StudentCannotBeEnrollException;

@Component
public class AppFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder defaultErrorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        if (HttpStatus.valueOf(response.status()).is4xxClientError()) {
            throw new StudentCannotBeEnrollException("Student cannot be enroll on course");
        }
        return defaultErrorDecoder.decode(methodKey, response);
    }
}
