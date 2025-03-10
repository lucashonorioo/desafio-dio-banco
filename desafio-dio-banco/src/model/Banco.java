package model;

import abstracts.Conta;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Banco {

    private String nomeAgencia;
    private int numeroAgencia;

    private List<Cliente> clientes;
    private List<Conta> contas;

    public Banco(String nomeAgencia, int numeroAgencia) {
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = numeroAgencia;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    public List<Conta> getContas() {
        return contas;
    }
   public void adicionarConta(Conta conta){
        contas.add(conta);
   }

    public Conta encontrarConta(int numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta() == numeroConta) {
                return conta;
            }
        }
        return null;
    }

}
