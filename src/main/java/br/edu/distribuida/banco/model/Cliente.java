package br.edu.distribuida.banco.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class Cliente implements Serializable {
    private String agencia;
    private String conta;
    private double valor;
    private String senha;
    private String numCartao;

}
