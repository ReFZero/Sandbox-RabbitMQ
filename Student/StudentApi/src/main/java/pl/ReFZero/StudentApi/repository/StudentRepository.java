package pl.ReFZero.StudentApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.ReFZero.StudentApi.model.Student;

import javax.validation.constraints.Email;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByStatus(Student.Status status);

    Boolean existsByEmail(String email);

    List<Student> findStudentsByEmailIn(List<String> emails);
}
