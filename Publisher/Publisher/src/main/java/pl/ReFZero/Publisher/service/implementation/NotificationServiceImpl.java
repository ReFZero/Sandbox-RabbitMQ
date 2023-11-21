package pl.ReFZero.Publisher.service.implementation;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.ReFZero.Publisher.model.Notification;
import pl.ReFZero.Publisher.model.Student;
import pl.ReFZero.Publisher.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

    public static final String URL_STUDENT_SERVICE = "http://localhost:8080/api/students/";
    private final RestTemplate restTemplate;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public NotificationServiceImpl(RestTemplate restTemplate, RabbitTemplate rabbitTemplate) {
        this.restTemplate = restTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendStudentNotification(Long studentId) {
        Student student = restTemplate.exchange(URL_STUDENT_SERVICE + studentId,
                HttpMethod.GET,
                HttpEntity.EMPTY,
                Student.class).getBody();

        Notification notification = createNotification(student);

        rabbitTemplate.convertAndSend("test", notification);
    }

    private static Notification createNotification(Student student) {
        Notification notification = new Notification();
        notification.setEmail(student.getEmail());
        notification.setTitle("Hej: " + student.getFirstName());
        notification.setBody(":" + student.getFirstName() + student.getLastName());
        return notification;
    }
}
