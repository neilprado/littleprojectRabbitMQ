package br.edu.distribuida.banco.service;

import br.edu.distribuida.banco.model.Banco;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class VisaReceiver {
    static final Logger logger = LoggerFactory.getLogger(VisaReceiver.class);

    @RabbitListener(queues = {"$visa.queue"})
    public void processPayment(Banco banco){
        logger.info("Pagamento processado pelo Visa com sucesso " + banco);
    }
}
