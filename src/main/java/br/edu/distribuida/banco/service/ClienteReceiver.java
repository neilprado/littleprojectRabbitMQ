package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.config.RabbitConfig;
import br.edu.distribuida.banco.model.Cliente;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ClienteReceiver {
    static final Logger logger = LoggerFactory.getLogger(ClienteReceiver.class);

    @RabbitListener(queues = RabbitConfig.QUEUE)
    public void processPayment(Cliente cliente){
        logger.info("Pagamento processado com sucesso " + cliente);
    }
}
