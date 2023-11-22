package pl.ReFZero.Courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import pl.ReFZero.Courses.model.Course;
import pl.ReFZero.Courses.repository.CourseRepository;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class CoursesApplication {

    private final CourseRepository courseRepository;

    @Autowired
    public CoursesApplication(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(CoursesApplication.class, args);
    }

    @PostConstruct
    public void createCoursesInDb() {
        courseRepository.saveAll(List.of(
                Course.builder()
                        .code("Java_2020")
                        .name("Java 17")
                        .descriptions("Java 17 - new")
                        .startDate(LocalDateTime.of(2023, 12, 23, 10, 0))
                        .endDate(LocalDateTime.of(2023, 12, 23, 14, 20))
                        .participantsLimit(5L)
                        .participantsNumber(0L)
                        .build(),
                Course.builder()
                        .code("SQL23")
                        .name("SQL2023")
                        .descriptions("SQL - new")
                        .startDate(LocalDateTime.of(2024, 2, 7, 11, 0))
                        .endDate(LocalDateTime.of(2024, 2, 7, 13, 40))
                        .participantsLimit(3L)
                        .participantsNumber(0L)
                        .build()
        ));
    }

}
