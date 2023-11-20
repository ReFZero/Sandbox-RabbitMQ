package pl.ReFZero.Receiver.configuration;


import org.springframework.amqp.support.converter.SimpleMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {
    @Bean
    public SimpleMessageConverter messageConverter() {
        SimpleMessageConverter converter = new SimpleMessageConverter();
        // Dodaje dozwolone wzorce nazw klas
        converter.addAllowedListPatterns("pl.ReFZero.notification.Notification");
        return converter;
    }
}
