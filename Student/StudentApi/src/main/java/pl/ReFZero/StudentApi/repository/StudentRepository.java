package pl.ReFZero.StudentApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ReFZero.StudentApi.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
