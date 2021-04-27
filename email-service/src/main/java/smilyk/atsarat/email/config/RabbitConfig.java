package smilyk.atsarat.email.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//TODO
// understans why dont create queue automatically
@Configuration
public class RabbitConfig {
    private static final String type = "direct";

    @Value(("${spring.rabbitmq.username}"))
    String rabbitUserName;
    @Value(("${spring.rabbitmq.password}"))
    String rabbitPassword;

    @Value(("${email.queue}"))
    String emailQueue;
    @Value(("${email.exchange}"))
    String emailExchange;
    @Value(("${email.key}"))
    String emailRoutingkey;

    @Value(("${conf.email.queue}"))
    String confEmailQueue;
    @Value(("${conf.email.key}"))
    String confEmailRoutingkey;



    @Bean
    public void createEmailQueue() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPassword(rabbitPassword);
        factory.setUsername(rabbitUserName);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(emailQueue, false, false, false, null);
            channel.exchangeDeclare(emailExchange, type, true);
            channel.queueBind(emailQueue, emailExchange, emailRoutingkey);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public void createConfEmailQueue() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPassword(rabbitPassword);
        factory.setUsername(rabbitUserName);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(confEmailQueue, false, false, false, null);
            channel.exchangeDeclare(emailExchange, type, true);
            channel.queueBind(confEmailQueue, emailExchange, confEmailRoutingkey);
        } catch (TimeoutException | IOException e) {
            e.printStackTrace();
        }
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
