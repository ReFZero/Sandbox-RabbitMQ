package pl.ReFZero.Receiver.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.ReFZero.Receiver.model.Notification;

import java.util.Optional;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("/notification")
    public ResponseEntity<Notification> receiveNotification() {
        Optional<Notification> notification = Optional.ofNullable(
                rabbitTemplate.receiveAndConvert(
                        "test",
                        ParameterizedTypeReference.forType(Notification.class)));
        if (notification.isPresent()) {
            return ResponseEntity.ok(notification.get());
        }
        return ResponseEntity.noContent().build();
    }


    @RabbitListener(queues = "test")
    public void listenerMessage(Notification notification) {
        System.out.println(notification.getEmail() + " " + notification.getTitle() + " " + notification.getBody());
    }
}
