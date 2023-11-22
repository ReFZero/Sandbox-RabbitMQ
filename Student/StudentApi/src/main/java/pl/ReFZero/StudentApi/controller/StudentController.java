package pl.ReFZero.StudentApi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.ReFZero.StudentApi.model.Student;
import pl.ReFZero.StudentApi.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<>(studentService.getAllStudents(), HttpStatus.OK);
    }

    @GetMapping("/students/{student_id}")
    public ResponseEntity<Student> getStudentById(@PathVariable(name = "student_id") Long studentId) {
        return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
    }

    @PostMapping("/students/add")
    public ResponseEntity<Student> addStudent(@RequestBody @Valid Student student) {
        return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.CREATED);
    }

    @DeleteMapping("/students/{student_id}/delete")
    public ResponseEntity<String> deleteStudent(@PathVariable(name = "student_id") Long studentId) {
        studentService.deleteStudent(studentId);
        return new ResponseEntity<>("Student deleted!", HttpStatus.ACCEPTED);
    }

    @PatchMapping("/students/{student_id}/update")
    public ResponseEntity<Student> updateStudentFirstName(
            @RequestParam("studentFirstName") String studentFirstName,
            @PathVariable("student_id") Long studentId) {
        return new ResponseEntity<>(studentService.updateStudentName(studentFirstName, studentId), HttpStatus.OK);
    }

    @PutMapping("/students/{student_id}/update")
    public ResponseEntity<Student> updateStudentData(
            @RequestBody @Valid Student studentUpdated,
            @PathVariable(name = "student_id") Long studentId) {
        return new ResponseEntity<>(studentService.updateStudentData(studentUpdated, studentId), HttpStatus.OK);
    }


}
