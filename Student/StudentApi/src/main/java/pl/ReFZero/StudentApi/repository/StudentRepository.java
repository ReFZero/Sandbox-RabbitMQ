package pl.ReFZero.StudentApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ReFZero.StudentApi.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByStatus(Student.Status status);
    Boolean existsByEmail(String email);
}
