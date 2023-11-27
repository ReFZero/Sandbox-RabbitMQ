package pl.ReFZero.Courses.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.ReFZero.Courses.model.dto.StudentDto;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE", path = "/students")
public interface StudentServiceClient {

    @GetMapping("/{studentId}")
    StudentDto getStudentById(@PathVariable Long studentId);

    @PostMapping("/emails")
    List<StudentDto> getStudentByEmails(@RequestBody List<String> emails);
}
