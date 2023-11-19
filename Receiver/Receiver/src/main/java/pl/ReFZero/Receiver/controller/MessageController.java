package pl.ReFZero.Receiver.controller;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class MessageController {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping("message")
    public String receiverMessage() {
        Optional<Object> message = Optional.ofNullable(rabbitTemplate.receiveAndConvert("test"));
        if (message.isPresent()) {
            return "Udalo sie pobrac wiadomosc: " + message.get();
        } else {
            return "Brak wiadomosci do odczytu!";
        }
    }

    @RabbitListener(queues = "test")
    public void listenerMessage(String message){
        System.out.println(message);
    }
}
