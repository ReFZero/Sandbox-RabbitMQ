package pl.ReFZero.Courses.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ReFZero.Courses.exception.customExceptions.CourseIsNotActiveException;
import pl.ReFZero.Courses.exception.customExceptions.CourseNotFoundException;
import pl.ReFZero.Courses.exception.customExceptions.CourseStudentIAAlreadyEnrolledException;
import pl.ReFZero.Courses.exception.customExceptions.CourseStudentIsNotActiveException;
import pl.ReFZero.Courses.model.Course;
import pl.ReFZero.Courses.model.CourseMember;
import pl.ReFZero.Courses.model.dto.Student;
import pl.ReFZero.Courses.repository.CourseRepository;
import pl.ReFZero.Courses.service.CourseService;
import pl.ReFZero.Courses.service.StudentServiceClient;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentServiceClient studentServiceClient;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentServiceClient studentServiceClient) {
        this.courseRepository = courseRepository;
        this.studentServiceClient = studentServiceClient;
    }

    @Override
    public List<Course> getCourses(Course.Status status) {
        if (status != null) {
            return courseRepository.findAllByStatus(status);
        }
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(String code) {
        return courseRepository.findById(code).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));
    }

    @Override
    public Course addCourse(Course course) {
        course.validateCourse();
        return courseRepository.save(course);
    }

    @Override
    public void courseEnrollment(String courseCode, Long studentId) {
        Course course = getCourse(courseCode);
        validateCourseStatus(course);
        Student student = studentServiceClient.getStudentById(studentId);
        validateStudentBeforeCourseEnrollment(course, student);
        course.incrementParticipantsNumber();
        course.getCourseMember().add(new CourseMember(student.getEmail()));
        courseRepository.save(course);
    }

    private static void validateStudentBeforeCourseEnrollment(Course course, Student student) {
        if (!Student.Status.ACTIVE.equals(student.getStatus())) {
            throw new CourseStudentIsNotActiveException("Student's status is not Active");
        }
        if (course.getCourseMember().stream()
                .anyMatch(member -> student.getEmail().equals(member.getEmail()))) {
            throw new CourseStudentIAAlreadyEnrolledException("Student already enrolled");
        }
    }

    private static void validateCourseStatus(Course course) {
        if (!Course.Status.ACTIVE.equals(course.getStatus())) {
            throw new CourseIsNotActiveException("Course is not Active!");
        }
    }
}
