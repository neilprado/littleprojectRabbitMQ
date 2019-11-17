package br.edu.distribuida.banco.controller;

import br.edu.distribuida.banco.model.Banco;
import br.edu.distribuida.banco.service.BancoSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class BancoController {
    private BancoSender sender;

    public BancoController(BancoSender sender) {
        this.sender = sender;
    }

    @PostMapping
    public Banco sendPayment(@RequestBody Banco banco){
        if(banco.getCodBandeira() == 0){
            sender.visaPayment(banco);
        }else{
            sender.masterPayment(banco);
        }

        return banco;
    }
}
