package pl.ReFZero.Courses.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import pl.ReFZero.Courses.exception.customExceptions.CourseCanNotSetFullStatusException;
import pl.ReFZero.Courses.exception.customExceptions.CourseParticipantsLimitIsExceededException;
import pl.ReFZero.Courses.exception.customExceptions.CourseStartAfterEndDateException;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course {

    @Id
    private String code;
    @NotBlank
    private String name;
    private String descriptions;
    @NotNull
    @Future
    private LocalDateTime startDate;
    @NotNull
    @Future
    private LocalDateTime endDate;
    @Min(0)
    private Long participantsLimit;
    @NotNull
    @Min(0)
    private Long participantsNumber;
    @NotNull
    private Status status;

    public enum Status {
        ACTIVE,
        INACTIVE,
        FULL
    }

    private void validateCourseDate() {
        if (startDate.isAfter(endDate)) {
            throw new CourseStartAfterEndDateException("Course start after end date");
        }
    }

    private void validateParticipantsLimit() {
        if (participantsNumber > participantsLimit) {
            throw new CourseParticipantsLimitIsExceededException("Course participants limit is exceeded");
        }
    }

    private void validateFullStatus() {
        if (Status.FULL.equals(status) && !participantsNumber.equals(participantsLimit)) {
            throw new CourseCanNotSetFullStatusException("Course can not set full status");
        }
    }

    public void validateCourse() {
        validateCourseDate();
        validateParticipantsLimit();
        validateFullStatus();
    }
}
