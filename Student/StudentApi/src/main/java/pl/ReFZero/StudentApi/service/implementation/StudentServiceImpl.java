package pl.ReFZero.StudentApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ReFZero.StudentApi.model.Student;
import pl.ReFZero.StudentApi.repository.StudentRepository;
import pl.ReFZero.StudentApi.service.StudentService;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<>(studentRepository.findAll());
    }

    @Override
    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(() -> new RuntimeException("Student could not be found!"));
    }

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student could not be found!"));
        studentRepository.delete(student);
    }

    @Override
    public Student updateStudentName(String newStudentFirstName, Long studentId) {
        Student studentFromDb = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student could not be found!"));
        studentFromDb.setFirstName(newStudentFirstName);
        return studentFromDb;
    }

    @Override
    public Student updateStudentData(Student updatedStudent, Long studentId) {
        Student studentFromDb = studentRepository.findById(studentId).orElse(null);
        if (studentFromDb != null) {
            studentFromDb.setFirstName(updatedStudent.getFirstName());
            studentFromDb.setLastName(updatedStudent.getLastName());
            studentFromDb.setEmail(updatedStudent.getEmail());
            return studentFromDb;
        }
        return studentRepository.save(updatedStudent);
    }
}
