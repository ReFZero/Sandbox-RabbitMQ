package pl.ReFZero.Courses.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import pl.ReFZero.Courses.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String> {

}
