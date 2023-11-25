package pl.ReFZero.Courses.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.ReFZero.Courses.model.dto.Student;

@FeignClient(name = "STUDENT-SERVICE", path = "/students")
public interface StudentServiceClient {

    @GetMapping("/{studentId}")
    Student getStudentById(@PathVariable Long studentId);
}
