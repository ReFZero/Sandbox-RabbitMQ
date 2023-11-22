package pl.ReFZero.StudentApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ReFZero.StudentApi.exception.customExceptions.StudentIsNotActiveException;
import pl.ReFZero.StudentApi.exception.customExceptions.StudentNotFoundException;
import pl.ReFZero.StudentApi.exception.customExceptions.StudentWithGivenEmailExistsException;
import pl.ReFZero.StudentApi.model.Student;
import pl.ReFZero.StudentApi.repository.StudentRepository;
import pl.ReFZero.StudentApi.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents(Student.Status status) {
        if (status != null) {
            return studentRepository.findAllByStatus(status);
        }
        return new ArrayList<>(studentRepository.findAll());
    }

    @Override
    public Student getStudentById(Long studentId) throws StudentIsNotActiveException {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
        if (!Student.Status.ACTIVE.equals(student.getStatus())) {
            throw new StudentIsNotActiveException("Student is not Active");
        }
        return student;
    }


    @Override
    public Student addStudent(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentWithGivenEmailExistsException("The student with the given email address exists");
        } else {
            return studentRepository.save(student);
        }
    }


    @Override
    public void deleteStudent(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
        student.setStatus(Student.Status.INACTIVE);
        studentRepository.save(student);
    }

    @Override
    public Student updateStudentName(String newStudentFirstName, Long studentId) {
        Student studentFromDb = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentNotFoundException("Student could not be found"));
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
            studentFromDb.setStatus(updatedStudent.getStatus());
            return studentRepository.save(studentFromDb);
        }
        return studentRepository.save(updatedStudent);
    }
}
