package pl.ReFZero.StudentApi.service;

import pl.ReFZero.StudentApi.model.Student;

import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();

    Student getStudentById(Long studentId);

    Student addStudent(Student student);

    void deleteStudent(Long id);

    Student updateStudentName(String newStudentFirstName, Long studentId);

    Student updateStudentData(Student updatedStudent, Long studentId);
}