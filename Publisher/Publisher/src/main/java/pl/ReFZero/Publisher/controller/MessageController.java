package pl.ReFZero.Publisher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.ReFZero.Publisher.service.NotificationService;

@RestController
public class MessageController {
    private final NotificationService notificationService;

    @Autowired
    public MessageController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/notifications")
    public String sendStudentNotification(@RequestParam Long studentId) {
        notificationService.sendStudentNotification(studentId);
        return "Wiadomosc zostala wysłana do Studenta o id: " + studentId;
    }
}
