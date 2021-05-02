package smilyk.atsarat.sceduler.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
/**
 * Configuration got RabbitMQ
 * creating queue and exchange for scheduler service
 */
@Configuration
public class RabbitProducerConfig {
    private static final String type = "direct";

    @Value("${rabbitmq.exchange}")
    String exchange;
    @Value(("${tsofim.queue}"))
    String tsofimQueue;
    @Value(("${spring.rabbitmq.username}"))
    String rabbitUserName;
    @Value(("${spring.rabbitmq.password}"))
    String rabbitPassword;
    @Value("${tsofim.key}")
    String tsofimRoutingkey;


    /**
     * creating email queue
     */
    @Bean
    public void createQuene() {
        com.rabbitmq.client.ConnectionFactory factory = new com.rabbitmq.client.ConnectionFactory();
        factory.setPassword(rabbitPassword);
        factory.setUsername(rabbitUserName);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(tsofimQueue, false, false, false, null);

            channel.exchangeDeclare(exchange, type, true);
            channel.queueBind(tsofimQueue, exchange, tsofimRoutingkey);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * creating exchange
     */
    @Bean
    DirectExchange exchange() {
        String name = exchange;
        return new DirectExchange(name, true, false);
    }
    /**
     * change messages for RabbitMQ to Json format
     */
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


}


