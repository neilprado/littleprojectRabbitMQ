package br.edu.distribuida.banco.controller;

import br.edu.distribuida.banco.model.Cliente;
import br.edu.distribuida.banco.service.ClienteSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteSender sender;

    public ClienteController(ClienteSender sender) {
        this.sender = sender;
    }

    @PostMapping
    public Cliente sendPayment(@RequestBody Cliente cliente){
        sender.sendPayment(cliente);
        return cliente;
    }
}
