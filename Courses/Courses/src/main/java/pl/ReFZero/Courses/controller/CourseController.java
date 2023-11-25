package pl.ReFZero.Courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ReFZero.Courses.model.Course;
import pl.ReFZero.Courses.service.CourseService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/{code}")
    public Course getCourse(@PathVariable String code) {
        return courseService.getCourse(code);
    }

    @GetMapping
    public List<Course> getCourses(
            @RequestParam(name = "status", required = false) Course.Status status) {
        return courseService.getCourses(status);
    }

    @PostMapping("/add")
    public Course addCourse(@Valid @RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PostMapping("/{courseCode}/student/{studentId}")
    public ResponseEntity<?> courseEnrollment(@PathVariable String courseCode,
                                              @PathVariable Long studentId) {
        courseService.courseEnrollment(courseCode, studentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
