package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.config.RabbitConfig;
import br.edu.distribuida.banco.model.Banco;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BancoSender {
    private final RabbitTemplate rabbitTemplate;

    private ObjectMapper mapper;

    @Autowired
    public BancoSender(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    public void visaPayment(Banco banco){
        this.rabbitTemplate.convertAndSend(RabbitConfig.VISA, banco);
    }

    public void masterPayment(Banco banco){
        this.rabbitTemplate.convertAndSend(RabbitConfig.MASTER, banco);
    }
}
