package com.danny.rabbitmq.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {

    Logger logger = LoggerFactory.getLogger(RabbitConfiguration.class);

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory();
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        template.setExchange("direct exchange");

        return template;
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("errorQueue");
    }

    @Bean
    public Queue warningAndInfoQueue() {
        return new Queue("warningAndInfoQueue");
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct exchange");
    }
//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("Common exchange");
//    }

    @Bean
    public Binding errorBinding() {
        return BindingBuilder.bind(errorQueue()).to(directExchange()).with("error");
    }

    @Bean
    public Binding warningBinding() {
        return BindingBuilder.bind(warningAndInfoQueue()).to(directExchange()).with("warning");
    }

    @Bean
    public Binding infoBinding() {
        return BindingBuilder.bind(warningAndInfoQueue()).to(directExchange()).with("info");
    }


    /**
     * Создание Listener'а через конфигурацию
     */
//    @Bean
//    public SimpleMessageListenerContainer messageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setConnectionFactory(connectionFactory());
//        container.setQueueNames("firstQueue");
//        container.setMessageListener(message ->
//                logger.info("Received from firstQueue: " + Arrays.toString(message.getBody())));
//
//        return container;
//    }
}

