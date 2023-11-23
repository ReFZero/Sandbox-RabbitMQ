package pl.ReFZero.Courses.service;

import pl.ReFZero.Courses.model.Course;

import java.util.List;

public interface CourseService {

    List<Course> getCourses(Course.Status status);
    Course getCourse(String code);
    Course addCourse(Course course);
}
