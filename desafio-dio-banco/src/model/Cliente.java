package model;

import abstracts.Conta;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Cliente {

    private String nome;

    private List<Conta> contas;

    public Cliente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void adiconarConta(Conta conta){
        contas.add(conta);
    }

}
