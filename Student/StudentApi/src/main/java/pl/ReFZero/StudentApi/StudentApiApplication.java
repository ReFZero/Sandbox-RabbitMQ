package pl.ReFZero.StudentApi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import pl.ReFZero.StudentApi.model.Student;
import pl.ReFZero.StudentApi.repository.StudentRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient
public class StudentApiApplication {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentApiApplication(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(StudentApiApplication.class, args);
    }

    @PostConstruct
    public void createStudent() {
        studentRepository.saveAll(
                List.of(Student.builder()
                                .firstName("David")
                                .lastName("Anders")
                                .email("david@gmail.com")
                                .status(Student.Status.ACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("George")
                                .lastName("Jones")
                                .email("jones@gmail.com")
                                .status(Student.Status.INACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("James")
                                .lastName("Anders")
                                .email("james@gmail.com")
                                .status(Student.Status.ACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("Sarah")
                                .lastName("Miller")
                                .email("Sarah@gmail.com")
                                .status(Student.Status.ACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("James")
                                .lastName("Davis")
                                .email("davis@gmail.com")
                                .status(Student.Status.INACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("Margaret")
                                .lastName("Taylor")
                                .email("Margaret@gmail.com")
                                .status(Student.Status.INACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("David")
                                .lastName("Lee")
                                .email("lee@gmail.com")
                                .status(Student.Status.ACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("Margaret")
                                .lastName("Harris")
                                .email("harris@gmail.com")
                                .status(Student.Status.INACTIVE)
                                .build(),
                        Student.builder()
                                .firstName("Gary")
                                .lastName("Robinson")
                                .email("robinson@gmail.com")
                                .status(Student.Status.ACTIVE)
                                .build()


                ));
    }
}