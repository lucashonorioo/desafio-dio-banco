package model;

import abstracts.Conta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cliente {

    private String nome;
    private List<Conta> contas;

    public Cliente(){

    }

    public String getNome() {
        return nome;
    }

    public Cliente(String nome) {
        this.nome = nome;
    }

    public void adiconarConta(Conta conta){
        contas.add(conta);
    }

}
