package model;

import abstracts.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Banco {

    private String nomeAgencia;
    private int numeroAgencia;

    private List<Cliente> clientes;
    private List<Conta> contas;

    public Banco(){
        super();
    }

    public Banco(String nomeAgencia,int numeroAgencia) {
        this.nomeAgencia = nomeAgencia;
        this.numeroAgencia = numeroAgencia;
        this.clientes = new ArrayList<>();
        this.contas = new ArrayList<>();
    }

   public void adiconarCliente(Cliente cliente){
        clientes.add(cliente);
   }

   public void adicionarConta(Conta conta){
        contas.add(conta);
   }

    public int getNumeroAgencia() {
        return numeroAgencia;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Banco banco = (Banco) o;
        return numeroAgencia == banco.numeroAgencia;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(numeroAgencia);
    }
}
