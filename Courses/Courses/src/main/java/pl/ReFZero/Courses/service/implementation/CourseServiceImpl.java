package pl.ReFZero.Courses.service.implementation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ReFZero.Courses.exception.customExceptions.*;
import pl.ReFZero.Courses.model.Course;
import pl.ReFZero.Courses.model.CourseMember;
import pl.ReFZero.Courses.model.dto.NotificationInfoDto;
import pl.ReFZero.Courses.model.dto.StudentDto;
import pl.ReFZero.Courses.repository.CourseRepository;
import pl.ReFZero.Courses.service.CourseService;
import pl.ReFZero.Courses.service.StudentServiceClient;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    public static final String EXHANGE_ENROLL_FINISH = "enroll_finish";
    private final CourseRepository courseRepository;
    private final StudentServiceClient studentServiceClient;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository, StudentServiceClient studentServiceClient, RabbitTemplate rabbitTemplate) {
        this.courseRepository = courseRepository;
        this.studentServiceClient = studentServiceClient;
        this.rabbitTemplate = rabbitTemplate;
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
        StudentDto student = studentServiceClient.getStudentById(studentId);
        validateStudentBeforeCourseEnrollment(course, student);
        course.incrementParticipantsNumber();
        course.getCourseMember().add(new CourseMember(student.getEmail()));
        courseRepository.save(course);
    }

    @Override
    public List<StudentDto> getCourseMembers(String courseCode) {
        Course course = getCourse(courseCode);
        List<String> emailsMembers = getCourseMembersEmails(course);
        return studentServiceClient.getStudentByEmails(emailsMembers);
    }

    @Override
    public void courseFinishEnroll(String courseCode) {
        Course course = getCourse(courseCode);

        if (Course.Status.INACTIVE.equals(course.getStatus())) {
            throw new CourseIsInactiveException("Course is inactive");
        }
        course.setStatus(Course.Status.INACTIVE);
        courseRepository.save(course);

        sendMessageToRabbitMq(course);
    }

    private void sendMessageToRabbitMq(Course course) {
        NotificationInfoDto notificationInfoDto = createNotificationInfo(course);
        rabbitTemplate.convertAndSend(EXHANGE_ENROLL_FINISH, notificationInfoDto);
    }

    private static NotificationInfoDto createNotificationInfo(Course course) {
        List<String> emailsMembers = getCourseMembersEmails(course);

        return NotificationInfoDto.builder()
                .courseCode(course.getCode())
                .courseName(course.getName())
                .courseDescription(course.getDescriptions())
                .courseStartDate(course.getStartDate())
                .courseEndDate(course.getEndDate())
                .emails(emailsMembers)
                .build();
    }

    private static List<String> getCourseMembersEmails(Course course) {
        return course.getCourseMember().stream()
                .map(CourseMember::getEmail).toList();
    }

    private static void validateStudentBeforeCourseEnrollment(Course course, StudentDto student) {
        if (!StudentDto.Status.ACTIVE.equals(student.getStatus())) {
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
