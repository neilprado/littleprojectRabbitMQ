package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.config.RabbitConfig;
import br.edu.distribuida.banco.model.Banco;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MasterSender {
    private final RabbitTemplate template;

    public MasterSender(RabbitTemplate template) {
        this.template = template;
    }

    public Banco processedMasterPayment(Banco banco){
        this.template.convertAndSend(RabbitConfig.VISA, banco);
        return banco;
    }
}
