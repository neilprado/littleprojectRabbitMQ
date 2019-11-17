package br.edu.distribuida.banco.config;

import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;


@Configuration
public class RabbitConfig implements RabbitListenerConfigurer {

    @Value("${visa.queue}")
    public static final String VISA = "visaQueue" ;

    @Value("${visa.exchange}")
    public static String VISA_EXCHANGE = "visaExchange";

    @Value("${master.queue}")
    public static String MASTER = "masterQueue";

    @Value("${master.exchange}")
    public static String MASTER_EXCHANGE = "masterExchange";

    @Bean
    Queue visaQueue(){
        return QueueBuilder.durable(VISA).build();
    }

    @Bean
    Queue masterQueue(){ return QueueBuilder.durable(MASTER).build(); }

    @Bean
    Exchange visaExchange(){
        return ExchangeBuilder.topicExchange(VISA_EXCHANGE).build();
    }

    @Bean
    Exchange masterExchange(){
        return ExchangeBuilder.topicExchange(MASTER_EXCHANGE).build();
    }

    @Bean
    Binding visaBinding(TopicExchange paymentExchange){
        return BindingBuilder.bind(visaQueue()).to(paymentExchange).with(VISA);
    }

    @Bean
    Binding masterBinding(TopicExchange paymentExchange){
        return BindingBuilder.bind(masterQueue()).to(paymentExchange).with(MASTER);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory(){
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter(){
        return new MappingJackson2MessageConverter();
    }
}
