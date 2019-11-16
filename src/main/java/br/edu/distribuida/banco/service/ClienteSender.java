package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.config.RabbitConfig;
import br.edu.distribuida.banco.model.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteSender {
    private final RabbitTemplate rabbitTemplate;

    private ObjectMapper mapper;

    @Autowired
    public ClienteSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void sendPayment(Cliente cliente){
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE, cliente);
    }
}
