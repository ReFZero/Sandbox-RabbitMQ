package pl.ReFZero.Courses.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ReFZero.Courses.exception.customExceptions.CourseNotFoundException;
import pl.ReFZero.Courses.model.Course;
import pl.ReFZero.Courses.repository.CourseRepository;
import pl.ReFZero.Courses.service.CourseService;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourse(String code) {
        return courseRepository.findById(code).orElseThrow(() -> new CourseNotFoundException("Course could not be found"));
    }

    @Override
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
