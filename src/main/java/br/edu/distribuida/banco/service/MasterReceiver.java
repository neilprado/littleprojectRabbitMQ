package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.model.Banco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MasterReceiver {
    private Logger logger = LoggerFactory.getLogger(MasterReceiver.class);

    @RabbitListener(queues = {"$master.queue"})
    public void processPayment(Banco banco){
        logger.info("Pagamento processado na MasterCard com sucesso" + banco);
    }

}
