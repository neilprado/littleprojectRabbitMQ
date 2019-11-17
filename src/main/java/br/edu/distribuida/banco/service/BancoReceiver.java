package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.model.Banco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class BancoReceiver {
    static final Logger logger = LoggerFactory.getLogger(BancoReceiver.class);

    @RabbitListener(queues = {"$visa.queue, $master.queue"})
    public void processPayment(Banco banco){
        logger.info("Pagamento processado com sucesso " + banco);
    }
}
